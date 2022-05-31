<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>휴가신청서(반차)</title>
</head>
<body>
	<table class="table table-bordered">
		<colgroup>
			<col style="width: 20%">
			<col style="width: 80%">
		</colgroup>
		<tr>
			<th>신청자</th>
			<td><p><span id="half_applier"></span></p></td>
		</tr>
		<tr>
			<th>반차사용예정일</th>
			<td><span id="half_date"></span></td>
		</tr>
		<tr>
			<th>반차종류</th>
			<td>
				<span id="half_kindof"></span>
			</td>
		</tr>
		<tr>
			<th>반차신청사유</th>
			<td><span id="half_reason"></span></td>
		</tr>
	</table>
</body>
</html>