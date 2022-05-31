<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>휴가신청서(병가)</title>
</head>
<body>
	<input type="hidden" name="documentType" value="html">
	<input type="hidden" name="documentNumber" value="30">
	<table class="table table-bordered">
		<colgroup>
			<col style="width: 20%">
			<col style="width: 80%">
		</colgroup>
		<tr>
			<th>신청자</th>
			<td><input type="text" data-type="approval" id="sick_applier" style="width: 99%" class="form-control"></td>
		</tr>
		<tr>
			<th>휴가시작일</th>
			<td><input type="date" data-type="approval" id="sick_sdate" style="width: 50%" class="form-control"></td>
		</tr>
		<tr>
			<th>휴가종료일</th>
			<td><input type="date" data-type="approval" id="sick_edate" style="width: 50%" class="form-control"></td>
		</tr>
		<tr>
			<th>질병종류</th>
			<td><input type="text" data-type="approval" id="sick_kindof" style="width: 99%" class="form-control"
				placeholder="병가의 경우 의사진단서를 첨부해야합니다."></td>
		</tr>
	</table>
</body>
</html>