<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" type="text/css" />  
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>  
<script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script> 
<content tag="local_script">
<script>
	$(function() {
		
		$("#datepicker").datepicker({
			changeMonth : true,
			changeYear : true,
			showButtonPanel : true,
			nextText : '다음 달',
			prevText : '이전 달',
			closeText : '닫기',
			dateFormat : 'yy-mm-dd'

		});
		
		var file = document.querySelector("#f_upload");//file upload전에 img 미리보기 구현
		file.onchange = function() {
			var fileList = file.files;
			
			var reader = new FileReader();
			reader.readAsDataURL(fileList[0]);
			
			reader.onload=function () {
				document.querySelector("#preview").src=reader.result;
				
			};
		};
		
	});
	
	function checkInsert() {
		if ($("#name").val() == "") {
			alertify.alert("사원명을 입력하세요");
			return false;
		}
		if ($("#datepicker").val() == "") {
			alertify.alert("입사일을 선택하세요");
			return false;
		}
		if ($("#tel").val() == "") {
			alertify.alert("전화번호를 입력하세요.");
			return false;
		}
		if ($("#addr").val() == "") {
			alertify.alert("주소를 입력하세요.");
			return false;
		}
		alert("등록 완료");
		return true;
	}
</script>
<style>
table {
	border-collapse: collapse;
}

td {
	font-size: 14px;
	padding:5px;
	align:center;
}

.mButton {
	width:150;
	height:150;
	margin:30px;
	border: 1px solid black;
	padding:10px;
}
.btnSubmit{
text-align:center;}

#f_upload{display:none;}

#deptCode{display:none;}
#preview{
width:150;
height:150;}


</style>
</content>
<title>사원 신규등록</title>
</head>
<body>
	<form method="post" action="/Groupware/HRmag/list.hm" name="frm" enctype="multipart/form-data" onsubmit="return checkInsert();">
		<table class="table table-striped">
		<tr>
		<th colspan="3" align="center">Employee Enrollment</th>
		</tr>
		<tr>
         <td rowspan="8" align="center">
         <div class="mButton"><img id="preview" src=""></div>
         <input type="file" name="f_upload" id="f_upload">
         <button type="button" class="btn btn-primary btn-xs" onclick="document.all.f_upload.click();">
         <span class="glyphicon glyphicon-search" aria-hidden="true"></span> 
         </button></td>
         </tr>
         <tr>
				<td width="50" align="center">사 원 명</td>
				<td><input type="text" name="emp_name" id="name"></td>
			</tr>

			<tr>
				<td width="50" align="center">입 사 일</td>
				<td><input type="text" id="datepicker" name="emp_hiredate"></td>

			</tr>
			<tr>
				<td align="center">부    서</td>
				<td><select id="sel_dept" name="dept_name">
						<option value="10">개발팀</option>
						<option value="20">인사팀</option>
						<option value="30">영업팀</option>
						<option value="40">고객지원팀</option>
				</select></td>
			</tr>


			<tr>
				<td align="center">직    급</td>
				<td><select id="position" name="emp_position">
						<option value="사원">사원</option>
						<option value="대리">대리</option>
						<option value="과장">과장</option>
						<option value="관리자">관리자</option>
						<option value="부장">부장</option>
						<option value="이사">이사</option>
						<option value="회장">회장</option>
				</select></td>
			</tr>

			<tr>
				<td align="center">전화번호</td>
				<td colspan="3"><input type="text" id="tel" name="emp_contact"></td>
			</tr>

			<tr> 
				<td align="center">주    소</td>
				<td colspan="3"><input type="text" id="addr" size="50" name="emp_address"></td>
			</tr>
			
			<tr> 
				<td align="center">비 밀 번 호</td>
				<td colspan="3"><input type="text" id="password" size="50" name="emp_password" value="1234"></td>
			</tr>
			<tr>
				<td colspan="4" align="center"><input class="btn btn-primary btn-sm"
					type="submit" value="등록"></td>
			</tr>
			
		</table>
	



	</form>
</body>
</html>