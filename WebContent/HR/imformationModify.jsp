<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<title>사원정보페이지</title>
<script src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
</head>
<body>
<form method="post" action="/Groupware/HR/updateC.imf" id="imformation2">
<table width="600" cellspacing="0" cellpadding="3"  align="center" class="table table-striped">
			<tr>
				<th colspan="3" style="text-align:center;">사 원 정 보 수 정</th>
			</tr>
			<tr>
				<th>사원명:</th>
				<td width="400"><input type="text" style="background-color:transparent;border:0 solid black;" name="emp_no"  value="${a.emp_no}" readonly></td>
			</tr>
			<tr>
				<th>사원번호:</th>
				<td width="400"><input type="text" style="background-color:transparent;border:0 solid black;" name="emp_name" value="${a.emp_name}" readonly></td>
			</tr>
			<tr>
				<th>비밀번호:</th>
				<td width="400"><input type="password" name="emp_password" value="${a.emp_password}"></td>
			</tr>
			<tr>
				<th>입사일:</th>
				<td width="400"><input type="text" style="background-color:transparent;border:0 solid black;" name="emp_hiredate" value="${a.emp_hiredate}" readonly></td>
			</tr>
			<tr>
				<th>부서번호:</th>
				<td width="400"><input type="text" style="background-color:transparent;border:0 solid black;" name="dept_code" value="${a.dept_code}" readonly></td>
			</tr>
			<tr>
				<th>직급:</th>
				<td width="400"><input type="text" style="background-color:transparent;border:0 solid black;" name="emp_position" value="${a.emp_position}" readonly></td>
			</tr>
			<tr>
				<th>주소:</th>
				<td width="400"><input type="text" name="emp_address" value="${a.emp_address}" ></td>
			</tr>
			<tr>
				<th>전화번호:</th>
				<td width="400"><input type="text" name="emp_contact"  value="${a.emp_contact}" ></td>
			</tr>
			<tr>
				<td colspan="3" align="right">
				 <input type="submit" value="등록" class="btn btn-primary">
				 <input type="button" value="취  소" onclick="javascript:window.location='/Groupware/Approval/main.jsp'" class="btn btn-primary">
				</td>
			</tr>
		</table>
</form>
</body>

</html>