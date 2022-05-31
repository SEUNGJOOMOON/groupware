<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>휴가신청서</title>
</head>
<body>
	<input type="hidden" name="documentType" value="html">
	<input type="hidden" name="documentNumber" value="40">
	<table class="table table-bordered">
		<colgroup>
			<col style="width: 20%">
			<col style="width: 80%">
		</colgroup>

		<tr>
			<th>신청자</th>
			<td><input type="text" data-type="approval" id="vac_applier" style="width: 99%" class="form-control"></td>
		</tr>
		<tr>
			<th>휴가시작일</th>
			<td><input type="date" data-type="approval" id="vac_sdate" style="width: 50%" class="form-control"></td>
		</tr>
		<tr>
			<th>휴가종료일</th>
			<td><input type="date" data-type="approval" id="vac_edate" style="width: 50%" class="form-control"></td>
		</tr>
		<tr>
			<th>사유</th>
			<td><input type="text" data-type="approval" id="vac_reason" style="width: 99%" class="form-control"></td>
		</tr>
	</table>
</body>
</html>