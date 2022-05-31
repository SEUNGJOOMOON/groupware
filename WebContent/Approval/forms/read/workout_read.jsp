<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>출장신청서</title>
</head>
<body>
	<table class="table table-bordered">
		<colgroup>
			<col style="width: 20%">
			<col style="width: 80%">
		</colgroup>

		<tr>
			<th>신청자</th>
			<td><span id="workout_applier"></span></td>
		</tr>
		<tr>
			<th>출장시작일</th>
			<td><span id="workout_sdate"></span></td>
		</tr>
		<tr>
			<th>출장종료일</th>
			<td><span id="workout_edate"></span></td>
		</tr>
		<tr>
			<th>사유</th>
			<td><span id="workout_reason"></span></td>
		</tr>
		<tr>
			<th>목적지</th>
			<td><span id="workout_destination"></span></td>
		</tr>
	</table>
</body>
</html>