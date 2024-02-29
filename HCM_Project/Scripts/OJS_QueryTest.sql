-- 즐겨찾기 결재자 등록
INSERT INTO SIGN_APPR_FAVO (SIAF_FAVO_CD, EMPL_ID, SIAF_FAVO_NAME,
							SIAF_APPR_ID, SIAF_APPR_FLAG)
VALUES ((SELECT NVL(MAX(SIAF_FAVO_CD) + 1, 50000001) FROM SIGN_APPR_FAVO), '20230108', '대리님',
		'20220101', 1);
	
-- 즐겨찾기 라인 등록
INSERT INTO SIGN_APPR_FAVO (SIAF_FAVO_CD, EMPL_ID, SIAF_FAVO_NAME,
							SIAF_APPR_LINE, SIAF_APPR_FLAG)
VALUES ((SELECT NVL(MAX(SIAF_FAVO_CD) + 1, 50000001) FROM SIGN_APPR_FAVO), '20230108', '업무보고용',
		'20220101, 20230104, 20230108 / 20220101, 20230105, 20230107', 2);

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
	

-- 결재문서 작성
INSERT INTO SIGN_DOC_BOX
							(SIDB_DOC_NUM, EMPL_ID, SIDB_DOC_TITLE,
							SIDB_DOC_CONTENT, SICA_CD, SIDB_DOC_EXPIREDT, 
							SIDT_TEMP_CD, 
							SIDB_DOC_JSON)
	VALUES					((SELECT NVL(MAX(SIDB_DOC_NUM) + 1, 24000001) FROM SIGN_DOC_BOX), '20230107', '[오지수]휴가 신청의 건',
							'여기에템플릿을넣어야해요', 'CC000001', '2024-03-05',
							'TC000002',
							'[
								{
									"APPR_ID":"20220101",
									"APPR_DEPTH":"1",
									"APPR_SIGN":"'|| (SELECT es.EMSI_SIGN_IMG  FROM EMPLOYEE e INNER JOIN EMP_SIGN es 
														ON E.EMPL_ID = ES.EMPL_ID WHERE E.EMPL_ID = '20220101') ||'",
									"APPR_DT":"",
									"APPR_FLAG":"0",
									"APPR_REPLY":""
								},
								{
									"APPR_ID":"20230105",
									"APPR_DEPTH":"2",
									"APPR_SIGN":"'|| (SELECT es.EMSI_SIGN_IMG  FROM EMPLOYEE e INNER JOIN EMP_SIGN es 
														ON E.EMPL_ID = ES.EMPL_ID WHERE E.EMPL_ID = '20230105') ||'",
									"APPR_DT":"",
									"APPR_FLAG":"0",
									"APPR_REPLY":""
								},
								{
									"APPR_ID":"20230106",
									"APPR_DEPTH":"3",
									"APPR_SIGN":"'|| (SELECT es.EMSI_SIGN_IMG  FROM EMPLOYEE e INNER JOIN EMP_SIGN es 
														ON E.EMPL_ID = ES.EMPL_ID WHERE E.EMPL_ID = '20230106') ||'",
									"APPR_DT":"",
									"APPR_FLAG":"0",
									"APPR_REPLY":""
								}
							]');

-- 문서별 결재라인 조회
SELECT sdb.SIDB_DOC_NUM AS 문서번호 , e.EMPL_NAME AS 결재자,
       appr_data.APPR_ID,
       appr_data.APPR_SIGN,
       appr_data.APPR_DT,
       appr_data.APPR_REPLY,
       appr_data.APPR_FLAG,
       appr_data.APPR_DEPTH
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
WHERE SIDB_DOC_NUM = 24000001;

-- 반려시 결재문서 업데이트(트랜잭션)
UPDATE SIGN_DOC_BOX
SET SIDB_DOC_FLAG = '2', SIDB_DOC_APPRDT = CURRENT_DATE, SIDB_DOC_STAT = '3'
WHERE SIDB_DOC_NUM = 24000001;


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
     )) appr_data ON sdb.SIDB_DOC_NUM = 24000001
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
WHERE SIDB_DOC_NUM = 24000001;

-- 최종승인시 문서 업데이트(트랜잭션)
UPDATE SIGN_DOC_BOX
SET SIDB_DOC_FLAG = '1', SIDB_DOC_APPRDT = CURRENT_DATE, SIDB_DOC_STAT = '3'
WHERE SIDB_DOC_NUM = 24000001;

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
	WHERE D.EMPL_ID = 20230107;







