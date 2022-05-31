<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>기안서</title>
</head>
<content tag="local_script"> <script>
	$(function() {
		CKEDITOR.replace("content");
	});
</script> </content>
<body>
	<input type="hidden" name="documentType" value="editor">
	<input type="hidden" name="documentNumber" value="10">
	<table class="table table-bordered">
		<colgroup>
			<col style="width: 20%">
			<col style="width: 80%">
		</colgroup>
		<tr>
			<th>내용</th>
			<td><textarea class="ckeditor" id="content"></textarea></td>
		</tr>
	</table>
</body>
</html>