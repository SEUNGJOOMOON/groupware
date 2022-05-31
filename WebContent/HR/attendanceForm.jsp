<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<script src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

</head>
<content tag="local_script"> <script type="text/javascript">
	$(function() {
		$("[name='att']").click(function() {
			$("input[name='atttype']").eq(0).val($(this).val());
			$.ajax({
				type : "post",
				url : "/Groupware/HR/check.at",
				data : "emp_no=${user_empno}",
				dataType : "xml",
				success : function(xml) {
					if ($(xml).find("result").text()=="attend"&&$("input[name='atttype']").eq(0).val() == '출근') {
						alert("오늘은 이미 출근이 기록되었습니다.");
					}else{
						var today = new Date();
						var h = today.getHours();
						
						if (h >= 9 && $("input[name='atttype']").eq(0).val() == '출근') {
							var retVal = prompt("지각사유를 입력하시오.", "");
							if (retVal != null) {
								$('[name="late_reason"]').val(retVal);
								$("#attForm").submit();
							}
						} else {
							$("#attForm").submit();
						}
					}
					
				},
				error : function(xhr, status, error) {
					alert("예상치 못한 에러가 발생하였습니다. 관리자에게 문의하세요. 에러코드 : " + xhr);
				}

			});

	
		});

	});
</script> </content>
<body>

	<form action="/Groupware/HR/update.at" method="post" id="attForm"
		name="attForm">
		<table class="table table-striped">
			<colgroup>
				<col style="width: 20%">
				<col style="width: 80%">
			</colgroup>
			<tr>
				<th colspan="2" style="text-align: center">출퇴근 관리</th>
			</tr>
			<tr>
				<th>사원명:</th>
				<td width="400"><span>${user_name}</span> <input type="hidden"
					name="emp_name" value="${user_name}"></td>
			</tr>
			<tr>
				<th>사원번호:</th>
				<td width="400"><span>${user_empno}</span> <input type="hidden"
					name="emp_no" value="${user_empno}" readonly></td>
			</tr>
			<tr>
				<th>부서명:</th>
				<td width="400"><span>${user_dept}</span> <input type="hidden"
					name="dept_name" value="${user_dept}"> <input type="hidden"
					name="late_reason"></td>
			</tr>
			<tr>
				<td colspan="2" align="right"><input type="button" value="출근"
					class="btn btn-primary" name="att"> <input type="hidden"
					name="atttype"> <input type="button" value="퇴근"
					class="btn btn-primary" name="att"></td>
			</tr>
		</table>
	</form>
	<table class="table table-striped">
		<colgroup>
			<col style="width: 16%">
			<col style="width: 20%">
			<col style="width: 16%">
			<col style="width: 16%">
			<col style="width: 16%">
			<col style="width: 16%">
		</colgroup>
		<tr style="text-aling: center">
			<th>사원번호</th>
			<th>사원이름</th>
			<th>부서</th>
			<th>날짜</th>
			<th>출근시간</th>
			<th>퇴근시간</th>
		</tr>
		<c:forEach var="list" items="${attList}">
			<tr>
				<td>${list.emp_no}</td>
				<td>${list.emp_name}</td>
				<td>${list.dept_name}</td>
				<td>${list.emp_date}</td>
				<td>${list.attend_time}</td>
				<td>${list.getoff_time}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>