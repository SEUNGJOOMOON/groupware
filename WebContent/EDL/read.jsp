<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><%@ taglib
	prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>문서함 읽기</title>
<content tag="local_script"> <script>
	$(function() {

		$("#view_pdf").draggable();
		$("#view_pdf").draggable({
			containment : "window"
		});
	});
</script> </content>
</head>
<body>
	<div class="panel panel-primary"
		style="width: 700px; height: 800px; position: absolute; z-index: 10000; display: none"
		id="view_pdf">
		<div class="panel-heading">
			${PDFInfor.doc_realname}<i class="glyphicon glyphicon-remove"
				style="float: right; cursor: pointer;"
				onclick="$('#view_pdf').hide();"></i>
		</div>
		<div class="panel-body">
			<embed src='${PDFInfor.doc_viewpath}' type='application/pdf'
				style="width: 100%; height: 700px">
		</div>
	</div>
	<table class="table table table-striped ">
		<colgroup>
			<col style="width: 20%">
			<col style="width: 90%">
		</colgroup>
		<tr>
			<td style="text-align: center">작성자</td>
			<td>
				<p id="displayName">${PDFInfor.doc_writer}</p> <input type="hidden"
				id="txt_name" name="txt_name">
			</td>
		</tr>
		<tr>
			<td style="text-align: center">작성부서</td>
			<td>
				<p id="displayDept">${PDFInfor.doc_dept}</p> <input type="hidden"
				id="displayDept" name="displayDept">
			</td>
		</tr>
		<tr>
			<td style="text-align: center">작성일</td>
			<td>
				<p id="displayDate">${PDFInfor.doc_date}</p> <input type="hidden"
				id="displayDate" name="displayDate">
			</td>
		</tr>
		<tr>
			<td style="text-align: center">제목</td>
			<td>
				<p id="displayTitle">${PDFInfor.doc_title}</p>
			</td>
		</tr>
		<tr>
			<td style="text-align: center">내용</td>
			<td>
				<div id="pdf" style="width: 100%; height: 260px">
					<embed src='${PDFInfor.doc_viewpath}' type='application/pdf'
						style="width: 100%; height: 90%">
				</div>
				<button type="button" class="btn btn-primary" id="btn_show_pdf"
					style="float: right; margin-right: 5px;"
					onclick="$('#view_pdf').show();">
					<i class="glyphicon glyphicon-plus"></i>
				</button>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<button type="button" class="btn btn-primary" style="float: right"
					onclick="javascript:history.go(-1);">뒤로</button> <c:if
					test="${user_position == '관리자'}">
					<button type="button" class="btn btn-primary"
						style="float: right; margin-right: 5px;"
						onclick="javascript:location.href = '/Groupware/EDL/delete.gw?doc_num=${PDFInfor.doc_num}'">삭제</button>
				</c:if>

			</td>
		</tr>
	</table>
</body>
</html>