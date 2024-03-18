SELECT sdb.SIDB_DOC_NUM , SIDB_DOC_TITLE, json.APPR_ID, json.APPR_DEPTH, e.EMPL_NAME , LAG(e.EMPL_NAME) OVER(PARTITION BY sdb.SIDB_DOC_NUM ORDER BY APPR_DEPTH) AS 최근결재자
FROM SIGN_DOC_BOX sdb ,
JSON_TABLE(sdb.SIDB_DOC_JSON, '$[*]' COLUMNS(
			APPR_ID PATH '$.APPR_ID',
			APPR_DEPTH PATH '$.APPR_DEPTH',
			APPR_FLAG PATH '$.APPR_FLAG',
			APPR_DT PATH '$.APPR_DT',
			APPR_REPLY PATH '$.APPR_REPLY',
			APPR_SIGN PATH '$.APPR_SIGN'
)) json JOIN EMPLOYEE e
ON json.APPR_ID = e.EMPL_ID 
WHERE APPR_ID IN (SELECT APPR_ID FROM SIGN_DOC_BOX)
ORDER BY SIDB_DOC_NUM



-- 즐겨찾기 결재자 등록
INSERT INTO SIGN_APPR_FAVO (SIAF_FAVO_CD, EMPL_ID, SIAF_FAVO_NAME,
							SIAF_APPR_ID, SIAF_APPR_FLAG)
VALUES ((SELECT NVL(MAX(SIAF_FAVO_CD) + 1, 50000001) FROM SIGN_APPR_FAVO), '20230108', '',
		'20220105', 1);
	
-- 즐겨찾기 라인 등록
INSERT INTO SIGN_APPR_FAVO (SIAF_FAVO_CD, EMPL_ID, SIAF_FAVO_NAME,
							SIAF_APPR_LINE, SIAF_APPR_FLAG)
VALUES ((SELECT NVL(MAX(SIAF_FAVO_CD) + 1, 50000001) FROM SIGN_APPR_FAVO), '20230108', '연차용',
		'20230105, 20230107', 2);

-- 즐겨찾기 결재자 불러오기
SELECT SIAF_FAVO_CD , SIAF_FAVO_NAME , SIAF_APPR_ID
	FROM SIGN_APPR_FAVO saf 
	WHERE SIAF_FAVO_CD = 50000001
	AND EMPL_ID = 20230108
	AND SIAF_APPR_FLAG = 1;

-- 즐겨찾기 라인 불러오기
SELECT SIAF_FAVO_CD , SIAF_FAVO_NAME , SIAF_APPR_LINE
	FROM SIGN_APPR_FAVO saf 
	WHERE SIAF_FAVO_CD = 50000002
	AND EMPL_ID = 20230108
	AND SIAF_APPR_FLAG = 2;

-- 즐겨찾기 수정
UPDATE SIGN_APPR_FAVO 
SET SIAF_APPR_ID = '20220101, 20230104, 20230108'
WHERE SIAF_FAVO_CD = 50000001
AND EMPL_ID = 20230108;

-- 즐겨찾기 삭제
DELETE FROM SIGN_APPR_FAVO
WHERE SIAF_FAVO_CD = 50000001
AND EMPL_ID = 20230108;

-- 즐겨찾기 목록 조회(결재자)
SELECT SIAF_FAVO_CD , SIAF_FAVO_NAME , SIAF_APPR_ID, e.EMPL_NAME 
	FROM SIGN_APPR_FAVO saf JOIN EMPLOYEE e
	ON saf.SIAF_APPR_ID = e.EMPL_ID 
	WHERE saf.EMPL_ID = 20230108
	AND SIAF_APPR_FLAG = 1;

-- 즐겨찾기 목록 조회(라인)
SELECT SIAF_FAVO_CD , SIAF_FAVO_NAME , SIAF_APPR_LINE
	FROM SIGN_APPR_FAVO saf
	WHERE saf.EMPL_ID = '20230108'
	AND SIAF_APPR_FLAG = 2;

-- 라인에서 결재자정보 가져오기
SELECT EMPL_ID, EMPL_NAME
	FROM EMPLOYEE e 
	WHERE EMPL_ID IN ('20220101', '20230104', '20230108');

-- 즐겨찾기 중복검사
SELECT SIAF_FAVO_CD , EMPL_ID , SIAF_APPR_ID , SIAF_APPR_FLAG 
	FROM SIGN_APPR_FAVO
	WHERE EMPL_ID = '20230108'
	AND SIAF_APPR_ID = '20230102';

-- 문서별 결재라인 조회
SELECT sdb.SIDB_DOC_NUM AS 문서번호, SIDB_DOC_TITLE , e.EMPL_NAME AS 결재자,
       appr_data.APPR_ID,
       appr_data.APPR_SIGN AS 서명이미지,
       appr_data.APPR_DT AS 결재일,
       appr_data.APPR_REPLY AS 첨언,
       appr_data.APPR_FLAG AS 결재처리,
       appr_data.APPR_DEPTH AS 결재순서
FROM SIGN_DOC_BOX sdb ,
     JSON_TABLE(sdb.SIDB_DOC_JSON , '$[*]' COLUMNS (
        APPR_ID PATH '$.APPR_ID',
        APPR_SIGN PATH '$.APPR_SIGN',
        APPR_DT PATH '$.APPR_DT',
        APPR_REPLY PATH '$.APPR_REPLY',
        APPR_FLAG PATH '$.APPR_FLAG',
        APPR_DEPTH PATH '$.APPR_DEPTH'
     )) appr_data JOIN EMPLOYEE e 
     ON appr_data.APPR_ID = e.EMPL_ID 
WHERE SIDB_DOC_NUM = 24000001;

-- 결재라인 업데이트 (승인)	
UPDATE SIGN_DOC_BOX
SET SIDB_DOC_JSON = JSON_TRANSFORM(
    SIDB_DOC_JSON,
    SET '$[0].APPR_DT' = ''|| TO_CHAR(CURRENT_DATE, 'YYYY-MM-DD') ||'',
        '$[0].APPR_REPLY' = '잘 다녀오세요^^',
        '$[0].APPR_FLAG' = '1'
)
WHERE SIDB_DOC_NUM = 24000001;

-- 승인시 결재문서 업데이트(트랜잭션)
UPDATE SIGN_DOC_BOX
SET SIDB_DOC_APPRDT = CURRENT_DATE, SIDB_DOC_STAT = '2'
WHERE SIDB_DOC_NUM = 24000001;

-- 결재라인 업데이트 (반려)	
UPDATE SIGN_DOC_BOX
SET SIDB_DOC_JSON = JSON_TRANSFORM(
    SIDB_DOC_JSON,
    SET '$[1].APPR_DT' = ''|| TO_CHAR(CURRENT_DATE, 'YYYY-MM-DD') ||'',
        '$[1].APPR_REPLY' = '휴가를 가? 안돼',
        '$[1].APPR_FLAG' = '2'
)
WHERE SIDB_DOC_NUM = 24000029;

-- 반려시 결재문서 업데이트(트랜잭션)
UPDATE SIGN_DOC_BOX
SET SIDB_DOC_FLAG = '2', SIDB_DOC_APPRDT = CURRENT_DATE, SIDB_DOC_STAT = '4', SIDB_FINAL_ID = '20220101'
WHERE SIDB_DOC_NUM = 24000029;


-- 업데이트 후 다음 결재자
SELECT sdb.SIDB_DOC_NUM AS 문서번호 , sdb.SIDB_DOC_TITLE AS 문서제목 , sdb.SIDB_DOC_EXPIREDT AS 결재마감기한 , e.EMPL_NAME AS 이번결재자
FROM SIGN_DOC_BOX sdb
JOIN JSON_TABLE(sdb.SIDB_DOC_JSON, '$[*]' COLUMNS (
        APPR_ID PATH '$.APPR_ID',
        APPR_SIGN PATH '$.APPR_SIGN',
        APPR_DT PATH '$.APPR_DT',
        APPR_REPLY PATH '$.APPR_REPLY',
        APPR_FLAG PATH '$.APPR_FLAG',
        APPR_DEPTH PATH '$.APPR_DEPTH'
     )) appr_data ON sdb.SIDB_DOC_NUM = 24000002
                    AND appr_data.APPR_DEPTH = (SELECT MIN(appr_data_inner.APPR_DEPTH)
                                               FROM JSON_TABLE(sdb.SIDB_DOC_JSON, '$[*]' COLUMNS (
                                                    APPR_DEPTH PATH '$.APPR_DEPTH',
                                                    APPR_FLAG PATH '$.APPR_FLAG'
                                                )) appr_data_inner
                                               WHERE appr_data_inner.APPR_FLAG = '0')
JOIN EMPLOYEE e ON appr_data.APPR_ID = e.EMPL_ID
WHERE sdb.SIDB_DOC_FLAG != '2' OR sdb.SIDB_DOC_FLAG IS NULL;

-- 최종승인 업데이트
UPDATE SIGN_DOC_BOX
SET SIDB_DOC_JSON = JSON_TRANSFORM(
    SIDB_DOC_JSON,
    SET '$[2].APPR_DT' = ''|| TO_CHAR(CURRENT_DATE, 'YYYY-MM-DD') ||'',
        '$[2].APPR_REPLY' = '최종승인합니다 잘 다녀오세요',
        '$[2].APPR_FLAG' = '1'
)
WHERE SIDB_DOC_NUM = 24000029;

-- 최종승인시 문서 업데이트(트랜잭션)
UPDATE SIGN_DOC_BOX
SET SIDB_DOC_FLAG = '1', SIDB_DOC_APPRDT = CURRENT_DATE, SIDB_DOC_STAT = '3', SIDB_FINAL_ID = '20230105'
WHERE SIDB_DOC_NUM = 24000029;

-- 내 결재문서 조회
SELECT D.SIDB_DOC_NUM AS 문서번호 , D.SIDB_DOC_WRITEDT AS 작성일 , E.EMPL_NAME AS 기안자 , 
		D.SIDB_DOC_TITLE AS 제목 , D.SIDB_DOC_CONTENT AS 내용 , C.SICA_NAME AS 구분 ,
		T.SIDT_TEMP_NAME AS 템플릿 , D.SIDB_DOC_FLAG AS 결재결과 , D.SIDB_DOC_STAT AS 결재상태 ,
		D.SIDB_DOC_APPRDT AS 최근결재일 , D.SIDB_DOC_EXPIREDT AS 마감기한
	FROM SIGN_DOC_BOX D 
	JOIN EMPLOYEE e 
	ON D.EMPL_ID = E.EMPL_ID 
	JOIN SIGN_CATEGORY C 
	ON D.SICA_CD = C.SICA_CD 
	JOIN SIGN_DOC_TEMP T
	ON D.SIDT_TEMP_CD = T.SIDT_TEMP_CD 
	WHERE D.EMPL_ID = 20230107
	AND D.SIDB_DOC_DELFLAG = 'N';

-- 결재문서 작성
INSERT INTO SIGN_DOC_BOX
							(SIDB_DOC_NUM, EMPL_ID, SIDB_DOC_TITLE,
							SIDB_DOC_CONTENT, SICA_CD, SIDB_DOC_EXPIREDT, 
							SIDT_TEMP_CD, SIDB_DOC_BE, SIDB_DOC_END,
							SIDB_CURR_ID,
							SIDB_DOC_JSON)
	VALUES					((SELECT NVL(MAX(SIDB_DOC_NUM) + 1, 24000001) FROM SIGN_DOC_BOX), '20230109', '출장가고싶습니다',
							'여기에템플릿을넣어야해요', 'CC000001', '2024-03-05',
							'TC000002', '2024-03-07', '2024-03-12',
							'20230107',
							'[
								{
									"APPR_ID":"20230107",
									"APPR_DEPTH":"1",
									"APPR_SIGN":"",
									"APPR_DT":"",
									"APPR_FLAG":"0",
									"APPR_REPLY":""
								},
								{
									"APPR_ID":"20230109",
									"APPR_DEPTH":"2",
									"APPR_SIGN":"",
									"APPR_DT":"",
									"APPR_FLAG":"0",
									"APPR_REPLY":""
								},
								{
									"APPR_ID":"20220101",
									"APPR_DEPTH":"3",
									"APPR_SIGN":"",
									"APPR_DT":"",
									"APPR_FLAG":"0",
									"APPR_REPLY":""
								}
							]');

-- 결재문서 삭제
UPDATE SIGN_DOC_BOX
SET SIDB_DOC_DELFLAG = 'N'
WHERE SIDB_DOC_NUM = 24000001;

-- 결재문서 수정
UPDATE SIGN_DOC_BOX 
SET SIDB_DOC_TITLE = '[오지수]업무일지', SIDB_DOC_CONTENT = '수정한 템플릿', SICA_CD = 'CC000003'
, SIDB_DOC_ALFLAG = 'N', SIDB_DOC_MODIFYDT = CURRENT_DATE, SIDT_TEMP_CD = 'TC000009',
SIDB_DOC_BE = '2024-03-02', SIDB_DOC_END = '2024-03-05',
EMPL_REF = '20230105, 20230106', EMPL_DEPT_CD = 'DT000004',
SIDB_DOC_JSON = '[
{
									"APPR_ID":"",
									"APPR_DEPTH":"",
									"APPR_SIGN":"",
									"APPR_DT":"",
									"APPR_FLAG":"",
									"APPR_REPLY":""
								},
								{
									"APPR_ID":"20230105",
									"APPR_DEPTH":"2",
									"APPR_SIGN":"",
									"APPR_DT":"",
									"APPR_FLAG":"0",
									"APPR_REPLY":""
								},
								{
									"APPR_ID":"20230106",
									"APPR_DEPTH":"3",
									"APPR_SIGN":"",
									"APPR_DT":"",
									"APPR_FLAG":"0",
									"APPR_REPLY":""
								}
				]'
WHERE SIDB_DOC_NUM = 24000002;

-- 임시보관함 저장
INSERT INTO SIGN_TEMP_BOX
							(SITB_DOC_NUM, EMPL_ID, SITB_DOC_TITLE,
							SITB_DOC_CONTENT, SICA_CD, SITB_DOC_EXPIREDT, 
							SIDT_TEMP_CD,
							SITB_DOC_JSON)
	VALUES					((SELECT NVL(MAX(SITB_DOC_NUM) + 1, 24000001) FROM SIGN_TEMP_BOX), '20230107', '[오지수]휴가 신청의 건',
							'여기에템플릿을넣어야해요', 'CC000001', '2024-03-05',
							'TC000002',
							'[
								{
									"APPR_ID":"20230105",
									"APPR_DEPTH":"2",
									"APPR_SIGN":"",
									"APPR_DT":"",
									"APPR_FLAG":"0",
									"APPR_REPLY":""
								},
								{
									"APPR_ID":"20230106",
									"APPR_DEPTH":"3",
									"APPR_SIGN":"",
									"APPR_DT":"",
									"APPR_FLAG":"0",
									"APPR_REPLY":""
								}
							]');
						
-- 첨부파일 등록
INSERT INTO SIGN_DOC_FILE (SIDF_FILE_NUM, SIDB_DOC_NUM, SIDF_FILE_ORIGIN,
							SIDF_FILE_STORED, SIDF_FILE_SIZE, SIDF_FILE_CONTENT)
VALUES ((SELECT NVL(MAX(SIDF_FILE_NUM) + 1, 24000001) FROM SIGN_DOC_FILE), 24000001, '파일.png',
		'qwjklejwpqreopjv', '39231294', '10001010111100111111');
	
-- 임시보관함 불러오기
SELECT SITB_DOC_NUM , SITB_DOC_WRITEDT , EMPL_ID ,
		SITB_DOC_TITLE , SITB_DOC_CONTENT , SICA_CD ,
		SITB_DOC_EXPIREDT , SITB_DOC_ALFLAG , SIDT_TEMP_CD ,
		SITB_DOC_JSON , EMPL_REF , EMPL_DEPT_CD , SITB_DOC_BE ,
		SITB_DOC_END 
	FROM SIGN_TEMP_BOX stb
	WHERE SITB_DOC_NUM = 24000001;

-- 결재트리 조회
SELECT ID, PARENT , TEXT , ICON , POS_NA , POS_FLAG 
	FROM SIGN_DOC_TREE ;

-- 결재트리 사원등록 트랜잭션
INSERT INTO SIGN_DOC_TREE (ID, PARENT, TEXT, 
							ICON, POS_NA, POS_FLAG)
VALUES ('20230133', 'DT000004', '전민균', 
		'glyphicon glyphicon-user', '인턴', '0');

-- 결재트리 정보변경 트랜잭션
UPDATE SIGN_DOC_TREE 
SET PARENT = 'DT000002', TEXT = '전민균', POS_NA = '사원', POS_FLAG = '1'
WHERE ID = '20230133';
	

SELECT EMPL_NAME , EMPL_ID , E.EMPL_DEPT_CD, CC2.COCO_NAME  , E.EMPL_RANK_CD , CC.COCO_NAME 
	FROM EMPLOYEE e 
	JOIN COMMON_CODE cc 
	ON E.EMPL_RANK_CD = CC.COCO_CD 
	JOIN COMMON_CODE cc2 
	ON E.EMPL_DEPT_CD = CC2.COCO_CD 
	ORDER BY 2;
	

INSERT INTO SIGN_DOC_TEMP (SIDT_TEMP_CD, SIDT_TEMP_NAME, SICA_CD, SIDT_TEMP_CREATEDT)
VALUES (
  'TC' || LPAD((SELECT MAX(SUBSTR(SIDT_TEMP_CD, 3)) + 1 FROM SIGN_DOC_TEMP), 6, '0'),
  '시말서', 'CC000005', CURRENT_DATE
);





SELECT appr_id, 
FROM 
( SELECT 
    jt.appr_id
   FROM
    SIGN_DOC_BOX SDB,
    JSON_TABLE(SDB.SIDB_DOC_JSON, '$[*]'
        COLUMNS (
            APPR_ID VARCHAR(10) PATH '$.APPR_ID',
            APPR_DEPTH INT PATH '$.APPR_DEPTH',
            APPR_SIGN VARCHAR(10) PATH '$.APPR_SIGN',
            APPR_DT VARCHAR(20) PATH '$.APPR_DT',
            APPR_FLAG VARCHAR(1) PATH '$.APPR_FLAG',
            APPR_REPLY VARCHAR(100) PATH '$.APPR_REPLY'
        )
    ) AS jt)
     WHERE APPR_ID = 20230106;
    
SELECT SDB.SIDB_DOC_NUM,
    SDB.SIDB_DOC_WRITEDT, SDB.SIDB_DOC_TITLE, SDB.SIDB_DOC_CONTENT,
    SDB.SICA_CD, SDB.SIDB_DOC_EXPIREDT, SDB.SIDB_DOC_FLAG,
    SDB.SIDB_DOC_APPRDT, SDB.SIDB_DOC_STAT, SDB.EMPL_REF AS 참조자,
    SDB.EMPL_DEPT_CD, E.EMPL_NAME AS 기안자, j.appr_id, e.EMPL_ID 
	FROM SIGN_DOC_BOX sdb JOIN EMPLOYEE e 
	ON SDB.EMPL_ID = E.EMPL_ID, 
	JSON_TABLE(SDB.SIDB_DOC_JSON, '$[*]' COLUMNS (
					APPR_ID PATH '$.APPR_ID',
					APPR_DEPTH PATH '$.APPR_DEPTH',
		            APPR_SIGN PATH '$.APPR_SIGN',
		            APPR_DT PATH '$.APPR_DT',
		            APPR_FLAG PATH '$.APPR_FLAG',
		            APPR_REPLY PATH '$.APPR_REPLY'
				)) j
	WHERE j.APPR_ID = '20230107'
    OR sdb.EMPL_ID = '20230107'
   
SELECT SDB.SIDB_DOC_NUM,
    SDB.SIDB_DOC_WRITEDT, SDB.SIDB_DOC_TITLE, SDB.SIDB_DOC_CONTENT,
    SDB.SICA_CD, SDB.SIDB_DOC_EXPIREDT, SDB.SIDB_DOC_FLAG,
    SDB.SIDB_DOC_APPRDT, SDB.SIDB_DOC_STAT, SDB.EMPL_REF AS,
    SDB.EMPL_DEPT_CD, SDB.SIDB_DOC_JSON,SDB .EMPL_ID ,  E.EMPL_NAME AS 기안자,APPR_id
FROM SIGN_DOC_BOX sdb
JOIN EMPLOYEE e ON sdb.EMPL_ID = e.EMPL_ID
CROSS JOIN JSON_TABLE(SDB.SIDB_DOC_JSON, '$[*]' COLUMNS (
               APPR_ID PATH '$.APPR_ID',
               APPR_DEPTH INT PATH '$.APPR_DEPTH',
               APPR_SIGN PATH '$.APPR_SIGN',
               APPR_DT PATH '$.APPR_DT',
               APPR_FLAG PATH '$.APPR_FLAG',
               APPR_REPLY PATH '$.APPR_REPLY'
            )) j
WHERE sdb.EMPL_ID = 20230106 OR j.APPR_ID = '20230106' OR INSTR(EMPL_REF, '20230106')> 0;


	
SELECT e.EMPL_ID, e.EMPL_NAME, c1.COCO_NAME, c2.COCO_NAME, c3.COCO_NAME
		FROM EMPLOYEE e JOIN COMMON_CODE c1
		ON e.EMPL_DEPT_CD = c1.COCO_CD
		JOIN COMMON_CODE c2
		ON e.EMPL_RANK_CD = c2.COCO_CD
		LEFT JOIN COMMON_CODE c3
		ON e.EMPL_POSITION_CD = c3.COCO_CD;

		
		
SELECT json.APPR_ID AS 결재자아이디, e.EMPL_NAME AS 결재자이름, json.APPR_DEPTH AS 결재순서
	FROM SIGN_DOC_BOX sdb ,
	JSON_TABLE(sdb.SIDB_DOC_JSON, '$[*]' COLUMNS(
		APPR_ID PATH '$.APPR_ID',
		APPR_DEPTH PATH '$.APPR_DEPTH'
	)) json JOIN EMPLOYEE e 
	ON json.APPR_ID = e.EMPL_ID
	WHERE sdb.SIDB_DOC_NUM = 24000001;
		
	
SELECT 
		COCO_CD "ID", 
		'#' "PARENT", 
		COCO_NAME "TEXT", 
		'glyphicon glyphicon-home' "ICON", 
		NULL "POS_NA", 
		NULL "POS_FLAG"
FROM 	COMMON_CODE
WHERE	SUBSTR(COCO_CD, 1, 2)  = 'DT'
AND 	COCO_PARENT_ID IS NOT NULL
UNION
SELECT 
		A.EMPL_ID "ID", 
		A.EMPL_DEPT_CD  "PARENT", 
		A.EMPL_NAME "TEXT", 
		'glyphicon glyphicon-user' "ICON", 
		C.COCO_NAME "POS_NA",
		C.RANK "POS_FLAG"
FROM 		EMPLOYEE A
INNER JOIN 	COMMON_CODE B ON A.EMPL_DEPT_CD = B.COCO_CD 
--INNER JOIN 	COMMON_CODE C ON A.EMPL_RANK_CD = C.COCO_CD
INNER JOIN	(
			SELECT 
					COCO_CD,
					COCO_NAME,
					ROW_NUMBER() OVER(ORDER BY COCO_CD ASC) RANK
			FROM 	COMMON_CODE
			WHERE	SUBSTR(COCO_CD, 1, 2)  = 'RK'
			AND 	COCO_PARENT_ID IS NOT NULL
			) C ON A.EMPL_RANK_CD = C.COCO_CD
ORDER BY "PARENT", "POS_FLAG";


SELECT COUNT(EMPL_ID) AS 인원수 , cc.COCO_NAME 부서
FROM EMPLOYEE e JOIN COMMON_CODE cc 
ON E.EMPL_DEPT_CD = CC.COCO_CD 
GROUP BY cc.COCO_NAME 






















UPDATE SIGN_DOC_BOX SET SIDB_CURR_ID =



(SELECT appr_data.APPR_ID
FROM SIGN_DOC_BOX sdb
JOIN JSON_TABLE(sdb.SIDB_DOC_JSON, '$[*]' COLUMNS (
        APPR_ID PATH '$.APPR_ID',
        APPR_SIGN PATH '$.APPR_SIGN',
        APPR_DT PATH '$.APPR_DT',
        APPR_REPLY PATH '$.APPR_REPLY',
        APPR_FLAG PATH '$.APPR_FLAG',
        APPR_DEPTH PATH '$.APPR_DEPTH'
     )) appr_data ON sdb.SIDB_DOC_NUM = 24000002
                    AND appr_data.APPR_DEPTH = (SELECT MIN(appr_data_inner.APPR_DEPTH)
                                               FROM JSON_TABLE(sdb.SIDB_DOC_JSON, '$[*]' COLUMNS (
                                                    APPR_DEPTH PATH '$.APPR_DEPTH',
                                                    APPR_FLAG PATH '$.APPR_FLAG'
                                                )) appr_data_inner
                                               WHERE appr_data_inner.APPR_FLAG = '0')
JOIN EMPLOYEE e ON appr_data.APPR_ID = e.EMPL_ID
WHERE sdb.SIDB_DOC_FLAG != '2' OR sdb.SIDB_DOC_FLAG IS NULL)