

# 구축환경
서버 : Apache Tomcat v8.5
개발툴 : Eclipse java EE IDE for Web Developers v : Neon.1a Prelease(4.6.1)
데이터베이스 : Oracle 11g xe
DBMS : SQL Developer, SQLGate

구축순서
1. 오라클 데이터베이스 셋업을 위해 DBTableQuery.txt파일 실행.
2. Groupware.zip 파일을 이클립스 내의 Import
3. web.xml의 WEB-INF/Properties파일 내의 Properties 파일들의
경로를 현재 시스템 경로로 변경.

최초 유저의 경우 사번 : admin 비밀번호 : 1234로
접속 후 유저 생성 및 그룹웨어에 접근이 가능합니다.

로그인 시 해당 유저의 정보는 세션을 통해 관리됩니다.

세션명 : user_name 값 : 유저명
세션명 : user_dept 값 : 부서명
세션명 : user_position 값 : 직위
세션명 : user_imgsrc 값 : 프로필사진 url
세션명 : user_address 값 : 주소
세션명 : user_contact 값 : 연락처
세션명 : user_hiredate 값 : 입사일
세션명 : user_empno 값 : 유저번호

기본 데이터베이스 접근정보는
localhost/scott/tiger
