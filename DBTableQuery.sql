-- SCOTT.APPROVALDOCUMENT
CREATE TABLE SCOTT.APPROVALDOCUMENT (
  APPDOC_NUM number(0) NOT NULL,
  APPDOC_NAME varchar2(100) NOT NULL,
  APPDOC_URL varchar2(100) NOT NULL,
  APPDOC_READURL varchar2(100) NOT NULL,
  APPDOC_TYPE varchar2(10) NOT NULL,
  PRIMARY KEY (APPDOC_NUM)
);

COMMENT ON COLUMN SCOTT.APPROVALDOCUMENT.APPDOC_NUM IS '양식종류번호';

COMMENT ON COLUMN SCOTT.APPROVALDOCUMENT.APPDOC_NAME IS '양식이름';

COMMENT ON COLUMN SCOTT.APPROVALDOCUMENT.APPDOC_URL IS '쓰기양식URL';

COMMENT ON COLUMN SCOTT.APPROVALDOCUMENT.APPDOC_READURL IS '읽기양식URL';

COMMENT ON COLUMN SCOTT.APPROVALDOCUMENT.APPDOC_TYPE IS '양식타입(editor,html)';


-- SCOTT.APPROVALLIST
CREATE TABLE SCOTT.APPROVALLIST (
  DOC_NUM number(0) NOT NULL,
  DOC_TITLE varchar2(100) NOT NULL,
  DOC_APPROVALLINE varchar2(4000) NOT NULL,
  DOC_APPROVALPROGRESSLINE varchar2(4000) NULL,
  DOC_EMPNAME varchar2(50) NOT NULL,
  DOC_EMPNO varchar2(50) NOT NULL,
  DOC_CONTENT varchar2(4000) NOT NULL,
  DOC_FILEURL varchar2(1000) NULL,
  DOC_FILENAME varchar2(200) NULL,
  DOC_STATE varchar2(10) NULL,
  DOC_TOTALLINECOUNT number(0) NOT NULL,
  DOC_CURRENTLINECOUNT number(0) NOT NULL,
  DOC_NEXTAPPROVALEMPNO varchar2(10) NOT NULL,
  DOC_TYPE varchar2(10) NULL,
  DOC_DOCUNUM number(0) NOT NULL,
  DOC_DRAFTDATE date(7) NULL,
  DOC_REFERENCES varchar2(1000) NULL,
  PRIMARY KEY (DOC_NUM)
);

ALTER TABLE SCOTT.APPROVALLIST
(
    CONSTRAINT SYS_C007258
    FOREIGN KEY ( APPDOC_NUM )
    REFERENCES SCOTT.APPROVALDOCUMENT( DOC_DOCUNUM )
);

COMMENT ON COLUMN SCOTT.APPROVALLIST.DOC_NUM IS '양식번호';

COMMENT ON COLUMN SCOTT.APPROVALLIST.DOC_TITLE IS '양식이름';

COMMENT ON COLUMN SCOTT.APPROVALLIST.DOC_APPROVALLINE IS '결재순서';

COMMENT ON COLUMN SCOTT.APPROVALLIST.DOC_APPROVALPROGRESSLINE IS '결재히스토리';

COMMENT ON COLUMN SCOTT.APPROVALLIST.DOC_EMPNAME IS '기안자이름';

COMMENT ON COLUMN SCOTT.APPROVALLIST.DOC_EMPNO IS '기안자사번';

COMMENT ON COLUMN SCOTT.APPROVALLIST.DOC_CONTENT IS '내용xml';

COMMENT ON COLUMN SCOTT.APPROVALLIST.DOC_FILEURL IS '첨부파일경로';

COMMENT ON COLUMN SCOTT.APPROVALLIST.DOC_FILENAME IS '첨부파일이름';

COMMENT ON COLUMN SCOTT.APPROVALLIST.DOC_STATE IS '현재결재상태';

COMMENT ON COLUMN SCOTT.APPROVALLIST.DOC_TOTALLINECOUNT IS '총결재횟수';

COMMENT ON COLUMN SCOTT.APPROVALLIST.DOC_CURRENTLINECOUNT IS '현재결재횟수';

COMMENT ON COLUMN SCOTT.APPROVALLIST.DOC_NEXTAPPROVALEMPNO IS '다음결재자사번';

COMMENT ON COLUMN SCOTT.APPROVALLIST.DOC_TYPE IS '양식종류(html,editor)';

COMMENT ON COLUMN SCOTT.APPROVALLIST.DOC_DOCUNUM IS '양식구분번호';

COMMENT ON COLUMN SCOTT.APPROVALLIST.DOC_DRAFTDATE IS '기안날짜';

COMMENT ON COLUMN SCOTT.APPROVALLIST.DOC_REFERENCES IS '참조자정보';


-- SCOTT.ATTENDANCE
CREATE TABLE SCOTT.ATTENDANCE (
  EMP_NAME varchar2(30) NULL,
  EMP_NO varchar2(30) NULL,
  DEPT_NAME varchar2(30) NULL,
  EMP_DATE varchar2(30) NULL,
  GETOFF_TIME varchar2(30) NULL,
  LATE_REASON varchar2(500) NULL,
  ATTEND_TIME varchar2(30) NULL
);

COMMENT ON COLUMN SCOTT.ATTENDANCE.EMP_NAME IS '사원이름';

COMMENT ON COLUMN SCOTT.ATTENDANCE.EMP_NO IS '사원번호';

COMMENT ON COLUMN SCOTT.ATTENDANCE.DEPT_NAME IS '부서이름';

COMMENT ON COLUMN SCOTT.ATTENDANCE.EMP_DATE IS '날짜';

COMMENT ON COLUMN SCOTT.ATTENDANCE.GETOFF_TIME IS '퇴근시간';

COMMENT ON COLUMN SCOTT.ATTENDANCE.LATE_REASON IS '지각사유';

COMMENT ON COLUMN SCOTT.ATTENDANCE.ATTEND_TIME IS '출근시간';


-- SCOTT.BOARD
CREATE TABLE SCOTT.BOARD (
  BOARD_NUM varchar2(1000) NOT NULL,
  BOARD_WRITERID varchar2(100) NOT NULL,
  BOARD_WRITER varchar2(100) NOT NULL,
  BOARD_TITLE varchar2(500) NOT NULL,
  BOARD_CONTENT long(0) NOT NULL,
  BOARD_HIT number(0) NULL,
  BOARD_DATE date(7) NULL,
  PRIMARY KEY (BOARD_NUM)
);

COMMENT ON COLUMN SCOTT.BOARD.BOARD_NUM IS '게시글번호(num)';

COMMENT ON COLUMN SCOTT.BOARD.BOARD_WRITERID IS '작성자사번(num)';

COMMENT ON COLUMN SCOTT.BOARD.BOARD_WRITER IS '작성자이름(name)';

COMMENT ON COLUMN SCOTT.BOARD.BOARD_TITLE IS '제목(title)';

COMMENT ON COLUMN SCOTT.BOARD.BOARD_CONTENT IS '내용(content)';

COMMENT ON COLUMN SCOTT.BOARD.BOARD_HIT IS '조회수(hit)';

COMMENT ON COLUMN SCOTT.BOARD.BOARD_DATE IS '작성날짜';


-- SCOTT.DEPT
CREATE TABLE SCOTT.DEPT (
  DEPT_NAME varchar2(255) NULL,
  DEPT_CODE varchar2(10) NOT NULL,
  DEPT_REPRESENT varchar2(255) NULL,
  PRIMARY KEY (DEPT_CODE)
);

COMMENT ON COLUMN SCOTT.DEPT.DEPT_NAME IS '부서이름';

COMMENT ON COLUMN SCOTT.DEPT.DEPT_CODE IS '부서code';

COMMENT ON COLUMN SCOTT.DEPT.DEPT_REPRESENT IS '부서대표';


-- SCOTT.ELECTRONIC_DOCUMENT
CREATE TABLE SCOTT.ELECTRONIC_DOCUMENT (
  DOC_NUM number(0) NOT NULL,
  DOC_WRITER varchar2(20) NULL,
  DOC_DEPT varchar2(20) NULL,
  DOC_TITLE varchar2(100) NULL,
  DOC_PATH varchar2(200) NULL,
  DOC_VIEWPATH varchar2(200) NULL,
  DOC_REALNAME varchar2(200) NULL,
  DOC_DATE date(7) NULL,
  PRIMARY KEY (DOC_NUM)
);

COMMENT ON COLUMN SCOTT.ELECTRONIC_DOCUMENT.DOC_NUM IS '글번호';

COMMENT ON COLUMN SCOTT.ELECTRONIC_DOCUMENT.DOC_WRITER IS '작성자이름';

COMMENT ON COLUMN SCOTT.ELECTRONIC_DOCUMENT.DOC_DEPT IS '부서이름';

COMMENT ON COLUMN SCOTT.ELECTRONIC_DOCUMENT.DOC_TITLE IS '제목content';

COMMENT ON COLUMN SCOTT.ELECTRONIC_DOCUMENT.DOC_PATH IS '첨부파일경로';

COMMENT ON COLUMN SCOTT.ELECTRONIC_DOCUMENT.DOC_VIEWPATH IS 'pdf출력경로';

COMMENT ON COLUMN SCOTT.ELECTRONIC_DOCUMENT.DOC_REALNAME IS '첨부파일이름';

COMMENT ON COLUMN SCOTT.ELECTRONIC_DOCUMENT.DOC_DATE IS '등록날짜';


-- SCOTT.EMP
CREATE TABLE SCOTT.EMP (
  EMP_NO varchar2(50) NOT NULL,
  EMP_NAME varchar2(50) NULL,
  DEPT_CODE varchar2(10) NULL,
  EMP_POSITION varchar2(255) NULL,
  EMP_ADDRESS varchar2(255) NULL,
  EMP_CONTACT varchar2(255) NULL,
  EMP_PROFILEIMG varchar2(255) NULL,
  EMP_PASSWORD varchar2(255) NULL,
  EMP_HIREDATE varchar2(255) NULL,
  PRIMARY KEY (EMP_NO)
);

ALTER TABLE SCOTT.EMP
(
    CONSTRAINT FK_CODE
    FOREIGN KEY ( DEPT_CODE )
    REFERENCES SCOTT.DEPT( DEPT_CODE )
);

COMMENT ON COLUMN SCOTT.EMP.EMP_NO IS '사번(num)';

COMMENT ON COLUMN SCOTT.EMP.EMP_NAME IS '직원이름(name)';

COMMENT ON COLUMN SCOTT.EMP.DEPT_CODE IS '부서코드(deptcode)';

COMMENT ON COLUMN SCOTT.EMP.EMP_POSITION IS '직급(position)';

COMMENT ON COLUMN SCOTT.EMP.EMP_ADDRESS IS '주소(address)';

COMMENT ON COLUMN SCOTT.EMP.EMP_CONTACT IS '연락처(number)';

COMMENT ON COLUMN SCOTT.EMP.EMP_PROFILEIMG IS '프로필이미지경로(path)';

COMMENT ON COLUMN SCOTT.EMP.EMP_PASSWORD IS '비밀번호(password)';

COMMENT ON COLUMN SCOTT.EMP.EMP_HIREDATE IS '입사일(date)';


-- SCOTT.PARTNER
CREATE TABLE SCOTT.PARTNER (
  PARTNER_NUM number(0) NOT NULL,
  PARTNER_CONTACT varchar2(100) NULL,
  PARTNER_ADDRESS varchar2(100) NULL,
  PARTNER_REPRECENT varchar2(100) NULL,
  PARTNER_COMPANYNAME varchar2(100) NULL,
  PARTNER_CONTRACTDATE date(7) NULL,
  PARTNER_TRADEITEM varchar2(100) NULL,
  PARTNER_IMAGE1 varchar2(500) NULL,
  PRIMARY KEY (PARTNER_NUM)
);

COMMENT ON COLUMN SCOTT.PARTNER.PARTNER_NUM IS '회사구분번호';

COMMENT ON COLUMN SCOTT.PARTNER.PARTNER_CONTACT IS '고객사연락처(contact)';

COMMENT ON COLUMN SCOTT.PARTNER.PARTNER_ADDRESS IS '고객사주소(address)';

COMMENT ON COLUMN SCOTT.PARTNER.PARTNER_REPRECENT IS '고객사대표직원(repre)';

COMMENT ON COLUMN SCOTT.PARTNER.PARTNER_COMPANYNAME IS '고객사명(name)';

COMMENT ON COLUMN SCOTT.PARTNER.PARTNER_CONTRACTDATE IS '거래시작날짜';

COMMENT ON COLUMN SCOTT.PARTNER.PARTNER_TRADEITEM IS '거래품목(item)';

COMMENT ON COLUMN SCOTT.PARTNER.PARTNER_IMAGE1 IS '거래처로고(logo)';


-- SCOTT.ROOM_RESERVE
CREATE TABLE SCOTT.ROOM_RESERVE (
  RESERVE_LISTNUM varchar2(100) NOT NULL,
  RESERVE_DATE varchar2(100) NULL,
  RESERVE_ROOM varchar2(100) NOT NULL,
  RESERVE_TIME varchar2(100) NOT NULL,
  RESERVE_NAME varchar2(100) NOT NULL,
  RESERVE_NUM varchar2(100) NOT NULL,
  RSR_DATE date(7) NOT NULL,
  PRIMARY KEY (RESERVE_LISTNUM)
);

COMMENT ON COLUMN SCOTT.ROOM_RESERVE.RESERVE_LISTNUM IS '예약구분번호(num)';

COMMENT ON COLUMN SCOTT.ROOM_RESERVE.RESERVE_DATE IS '예약날짜';

COMMENT ON COLUMN SCOTT.ROOM_RESERVE.RESERVE_ROOM IS '예약회의실(room)';

COMMENT ON COLUMN SCOTT.ROOM_RESERVE.RESERVE_TIME IS '예약시간(time)';

COMMENT ON COLUMN SCOTT.ROOM_RESERVE.RESERVE_NAME IS '예약자명(name)';

COMMENT ON COLUMN SCOTT.ROOM_RESERVE.RESERVE_NUM IS '예약자사번(empno)';

COMMENT ON COLUMN SCOTT.ROOM_RESERVE.RSR_DATE IS '예약한날짜';

-- 시퀀스 생성


create sequence seq_appdocu;
create sequence seq_docu;
create sequence seq_EDL;
create sequence seq_partner;
CREATE SEQUENCE emp_seq;
CREATE SEQUENCE board_seq;

-- 관리자 샘플 계정 생성
insert into emp values('admin', '관리자','10', '관리자','서울', '01011112222', 'noimg', '1234', '2016-01-01');

commit;