/********************************************
프로젝트 공통부분 시작
********************************************/

--부서상위
INSERT INTO COMMON_CODE (COCO_CD, COCO_NAME, COCO_PARENT_ID, COCO_CREATE_ID)
VALUES('DT000001', '부서', NULL, 'SYSTEM');
--부서하위
INSERT INTO COMMON_CODE (COCO_CD, COCO_NAME, COCO_PARENT_ID, COCO_CREATE_ID)
VALUES('DT000002', '인사팀', 'DT000001', 'SYSTEM');


INSERT INTO COMMON_CODE (COCO_CD, COCO_NAME, COCO_PARENT_ID, COCO_CREATE_ID)
VALUES('DT000002', '인사팀', (
							SELECT 
									COCO_CD
							FROM 	COMMON_CODE
							WHERE 	COCO_DELFLAG = 'N'
							AND		SUBSTR(COCO_CD, 1, 2) = 'DT'
							AND		COCO_PARENT_ID IS NULL
							), 'SYSTEM');


INSERT INTO COMMON_CODE (COCO_CD, COCO_NAME, COCO_PARENT_ID, COCO_CREATE_ID)
VALUES('DT000003', '총무팀', 'DT000001', 'SYSTEM');
INSERT INTO COMMON_CODE (COCO_CD, COCO_NAME, COCO_PARENT_ID, COCO_CREATE_ID)
VALUES('DT000004', '기획팀', 'DT000001', 'SYSTEM');


--직위상위
INSERT INTO COMMON_CODE (COCO_CD, COCO_NAME, COCO_PARENT_ID, COCO_CREATE_ID)
VALUES('RK000001', '직위', NULL, 'SYSTEM');
--직위하위
INSERT INTO COMMON_CODE (COCO_CD, COCO_NAME, COCO_PARENT_ID, COCO_CREATE_ID)
VALUES('RK000002', '사원', 'RK000001', 'SYSTEM');
INSERT INTO COMMON_CODE (COCO_CD, COCO_NAME, COCO_PARENT_ID, COCO_CREATE_ID)
VALUES('RK000003', '대리', 'RK000001', 'SYSTEM');
INSERT INTO COMMON_CODE (COCO_CD, COCO_NAME, COCO_PARENT_ID, COCO_CREATE_ID)
VALUES('RK000004', '과장', 'RK000001', 'SYSTEM');
INSERT INTO COMMON_CODE (COCO_CD, COCO_NAME, COCO_PARENT_ID, COCO_CREATE_ID)
VALUES('RK000005', '차장', 'RK000001', 'SYSTEM');
INSERT INTO COMMON_CODE (COCO_CD, COCO_NAME, COCO_PARENT_ID, COCO_CREATE_ID)
VALUES('RK000006', '부장', 'RK000001', 'SYSTEM');
INSERT INTO COMMON_CODE (COCO_CD, COCO_NAME, COCO_PARENT_ID, COCO_CREATE_ID)
VALUES('RK000007', '상무', 'RK000001', 'SYSTEM');
INSERT INTO COMMON_CODE (COCO_CD, COCO_NAME, COCO_PARENT_ID, COCO_CREATE_ID)
VALUES('RK000008', '전무', 'RK000001', 'SYSTEM');
INSERT INTO COMMON_CODE (COCO_CD, COCO_NAME, COCO_PARENT_ID, COCO_CREATE_ID)
VALUES('RK000009', '부사장', 'RK000001', 'SYSTEM');
INSERT INTO COMMON_CODE (COCO_CD, COCO_NAME, COCO_PARENT_ID, COCO_CREATE_ID)
VALUES('RK000010', '사장', 'RK000001', 'SYSTEM');


--직책상위
INSERT INTO COMMON_CODE (COCO_CD, COCO_NAME, COCO_PARENT_ID, COCO_CREATE_ID)
VALUES('PN000001', '직책', NULL, 'SYSTEM');
--직책하위
INSERT INTO COMMON_CODE (COCO_CD, COCO_NAME, COCO_PARENT_ID, COCO_CREATE_ID)
VALUES('PN000002', '팀장', 'PN000001', 'SYSTEM');
INSERT INTO COMMON_CODE (COCO_CD, COCO_NAME, COCO_PARENT_ID, COCO_CREATE_ID)
VALUES('PN000003', '본부장', 'PN000001', 'SYSTEM');
INSERT INTO COMMON_CODE (COCO_CD, COCO_NAME, COCO_PARENT_ID, COCO_CREATE_ID)
VALUES('PN000004', '대표이사', 'PN000001', 'SYSTEM');


--발령상위
INSERT INTO COMMON_CODE (COCO_CD, COCO_NAME, COCO_PARENT_ID, COCO_CREATE_ID)
VALUES('OR000001', '발령', NULL, 'SYSTEM');
--발령하위
INSERT INTO COMMON_CODE (COCO_CD, COCO_NAME, COCO_PARENT_ID, COCO_CREATE_ID)
VALUES('OR000002', '부서발령', 'OR000001', 'SYSTEM');
INSERT INTO COMMON_CODE (COCO_CD, COCO_NAME, COCO_PARENT_ID, COCO_CREATE_ID)
VALUES('OR000003', '직위발령', 'OR000001', 'SYSTEM');
INSERT INTO COMMON_CODE (COCO_CD, COCO_NAME, COCO_PARENT_ID, COCO_CREATE_ID)
VALUES('OR000004', '직책발령', 'OR000001', 'SYSTEM');

--권한코드 정보 입력
INSERT INTO AUTH_CODE (AUCO_CD, AUCO_NAME, AUCO_CREATE_ID) VALUES('ROLE_USER','일반사용자','SYSTEM');
INSERT INTO AUTH_CODE (AUCO_CD, AUCO_NAME, AUCO_CREATE_ID) VALUES('ROLE_HR_ADMIN','인사관리자','SYSTEM');
INSERT INTO AUTH_CODE (AUCO_CD, AUCO_NAME, AUCO_CREATE_ID) VALUES('ROLE_DOC_ADMIN','결재관리자','SYSTEM');
INSERT INTO AUTH_CODE (AUCO_CD, AUCO_NAME, AUCO_CREATE_ID) VALUES('ROLE_SM_ADMIN','일정관리자','SYSTEM');
INSERT INTO AUTH_CODE (AUCO_CD, AUCO_NAME, AUCO_CREATE_ID) VALUES('ROLE_SYSTEM','시스템관리자','SYSTEM');

/********************************************
프로젝트 공통부분 종료
********************************************/
