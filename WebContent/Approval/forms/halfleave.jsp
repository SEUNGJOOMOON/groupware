<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>휴가신청서(반차)</title>
</head>
<body>
<input type="hidden" name="documentType" value="html">
<input type="hidden" name="documentNumber" value="20">
	<table class="table table-bordered">
		<colgroup>
			<col style="width: 20%">
			<col style="width: 80%">
		</colgroup>
		<tr>
			<th>신청자</th>
			<td><input type="text" data-type="approval" id="half_applier" style="width: 99%" class="form-control"></td>
		</tr>
		<tr>
			<th>반차사용예정일</th>
			<td><input type="date" data-type="approval" id="half_date" style="width: 50%" class="form-control"></td>
		</tr>
		<tr>
			<th>반차종류</th>
			<td>
				<select data-type="approval" id="half_kindof">
					<option value="오전(09:00~13:00)">오전(09:00~13:00)</option>
					<option value="오후(13:00~)">오후(13:00~)</option>
				</select>
			</td>
		</tr>
		<tr>
			<th>반차신청사유</th>
			<td><input type="text" data-type="approval" id="half_reason" style="width: 99%" class="form-control" ></td>
		</tr>
	</table>
</body>
</html>