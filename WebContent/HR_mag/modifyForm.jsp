<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	<%@ page import="java.util.List"%>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" type="text/css" />  
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>  
<script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script> 
<script type="text/javascript">
function confirm_update(){
	alertify.alert("수정 완료되었씁니다.");
	document.location.href="/Groupware/HRmag/getList.hm";
}

</script>
<style>
th {
	text-align: center;
}
</style>

<title>사원정보 수정</title>
</head>
<body>
		<form method="post" name="frm" action="/Groupware/HRmag/modify.hm?pageNum=${pageNum}&emp_no=${hrList.emp_no}">
		<table class="table table-striped">
		 
         <tr>
         <th colspan="2">사원 정보</th>
         </tr>
         
  <tr>
  <td align="center">사원코드</td>
  <td width=50%>${hrList.emp_no }</td>
  </tr>
         <tr>
				<td align="center">사 원 명</td>
				<td width=50%><input type="text" value="${hrList.emp_name }" name="emp_name"></td>
				</tr>
				<tr>
				<td align="center">부    서</td>
				<td width=50%><select id="sel_dept" name="dept_name">
						<option value="10">기획팀</option>
						<option value="20">회계팀</option>
						<option value="30">개발팀</option>
						<option value="40">영업팀</option>
				</select></td></tr>
				<tr>
				
				<td align="center">직    급</td>
				<td width=50%><select id="position" name="emp_position">
						<option value="사원">사원</option>
						<option value="대리">대리</option>
						<option value="과장">과장</option>
						<option value="관리자">관리자</option>
						<option value="부장">부장</option>
						<option value="이사">이사</option>
						<option value="회장">회장</option>
				</select></td></tr>
				<tr>
				<td align="center">전화번호</td>
				<td width=50%><input type="text" value="${hrList.emp_contact }" name="emp_contact"></td>
				</tr>
				<tr>
				<td align="center">주    소</td>
				<td width=50%><input type="text" value="${hrList.emp_address }" name="emp_address"></td>
				</tr>
				<tr>
				<td align="center">P.W</td>
				<td width=50%><input type="text" value="${hrList.emp_password }" name="emp_password"></td>
				</tr>
			<tr>
			<td align="center" colspan="2">
				<input class="btn btn-primary btn-sm" type="submit" value="수정">
		        <td>
		        </td>
		        	</table> 	
	</form>
</body>
</html>