s<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>문서함</title>
<content tag="local_script"> <script>
	$(function() {
		$("#pop_up").draggable();
		$("#pop_up").draggable({
			containment : "window"
		});
		$("#insert_frm").submit(function() {
			if (!$("#txt_title").val()) {
				alertify.alert("제목을 입력해주세요.");
				$("#txt_title").focus();
				return false;
			}
			if (!$("#file_path").val()) {
				alertify.alert("파일을 업로드 해주세요.");
				$("#file_path").focus();
				return false;
			}
			return true;
		});

		$("#frm_search").submit(function() {
			if (!$("#title").val()) {
				alertify.alert("검색할 제목을 입력하세요.");
				return false;
			}
			return true;
		});
	});
</script> </content>
</head>
<body>
	<div id="pop_up"
		style="width: 100%; max-width: 500px; height: 100%; max-height: 300px; position: absolute; left: 20%; top: 30%; display: none; z-index: 10000"
		class="panel panel-primary">
		<div class="panel-heading">
			문서등록<i class="glyphicon glyphicon-remove"
				style="float: right; cursor: pointer;"
				onclick="$('#pop_up').hide();"></i>
		</div>
		<div class="panel-body">
			<form name="insert_frm" id="insert_frm" method="post"
				action="/Groupware/EDL/insert.gw" enctype="multipart/form-data">
				<table class="table table">
					<colgroup>
						<col style="width: 20%">
						<col style="width: 90%">
					</colgroup>
					<tr>
						<td style="text-align: center">작성자</td>
						<td>
							<p id="displayName">${sessionScope.user_name}</p> <input
							type="hidden" id="txt_name" value="${sessionScope.user_name}"
							name="txt_name">
						</td>
					</tr>
					<tr>
						<td style="text-align: center">작성부서</td>
						<td>
							<p id="displayDept">${sessionScope.user_dept}</p> <input
							type="hidden" value="${sessionScope.user_dept}" id="txt_dept"
							name="txt_dept">
						</td>
					</tr>
					<tr>
						<td style="text-align: center">제목</td>
						<td><input type="text" id="txt_title" name="txt_title"
							style="width: 99%"></td>
					</tr>
					<tr>
						<td style="text-align: center">첨부파일</td>
						<td><input type="file" id="file_path" accept=".pdf"
							name="file_path"></td>
					</tr>
					<tr>
						<td colspan="2">
							<button type="button" class="btn btn-primary"
								style="float: right; margin-left: 5px"
								onclick="javascript:$('#pop_up').hide();">닫기</button>
							<button type="submit" id="btn_regi_pdf" class="btn btn-primary"
								style="float: right">등록하기</button>

						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<c:if test="${pageCount.totalCnt == 0 }">
		<!-- 게시글이 없을 때 -->
		<table class="table">
			<colgroup>
				<col style="width: 20%">
				<col style="width: 40%">
				<col style="width: 20%">
				<col style="width: 20%">
			</colgroup>
			<tr>
				<td style="text-align: center" class="fullsize">글번호</td>
				<td style="text-align: center">제목</td>
				<td style="text-align: center">작성부서</td>
				<td style="text-align: center" class="fullsize">작성일</td>
			</tr>
			<tr>
				<td colspan="4" style="text-align: center" class="fullsize">결과가
					없습니다.</td>
			</tr>
			<tr>
				<td colspan="4" class="fullsize">
					<button type="button" class="btn btn-primary"
						style="float: right; margin-left: 5px"
						onclick="javascript:history.go(-1);">뒤로</button>
					<button type="button" id="btn_upload_pdf"
						class="btn btn-primary fullsize" style="float: right"
						onclick="javascript:$('#pop_up').show();">문서등록</button>

				</td>
			</tr>
		</table>
	</c:if>
	<c:if test="${pageCount.totalCnt != 0 }">
		<span style="float: right">[현재 페이지 : ${pageCount.pageNum }/전체
			페이지 : ${pageCount.totalPageCnt }]</span>
		<table class="table">
			<colgroup>
				<col style="width: 20%" class="fullsize">
				<col style="width: 40%">
				<col style="width: 20%">
				<col style="width: 20%" class="fullsize">
			</colgroup>
			<tr>
				<td style="text-align: center" class="fullsize">글번호</td>
				<td style="text-align: center">제목</td>
				<td style="text-align: center">작성부서</td>
				<td style="text-align: center" class="fullsize">작성일</td>
			</tr>
			<c:forEach var="list" items="${PDFList}">
				<tr>
					<td style="text-align: center" class="fullsize">${(pageCount.totalCnt - list.rowNum) + 1}</td>
					<td style="text-align: center"><a
						href="/Groupware/EDL/view.gw?doc_num=${list.doc_num}">${list.doc_title}</a></td>
					<td style="text-align: center">${list.doc_dept}</td>
					<td style="text-align: center" class="fullsize">${list.doc_date}</td>
				</tr>
			</c:forEach>
			<tr>
				<td class="fullsize"></td>
				<td style="text-align: center" colspan="2"><c:if
						test="${pageCount.pageNum != 1 && pageCount.searchKeyword != null}">
						<a
							href="/Groupware/EDL/search.gw?pageNum=${pageCount.pageNum-1 }&title=${pageCount.searchKeyword }">
							◀ 이전 </a>
					</c:if> <c:if
						test="${pageCount.pageNum != pageCount.totalPageCnt && pageCount.searchKeyword != null}">
						<a
							href="/Groupware/EDL/search.gw?pageNum=${pageCount.pageNum+1 }&title=${pageCount.searchKeyword }">
							다음 ▶</a>
					</c:if> <c:if
						test="${pageCount.pageNum != 1 && pageCount.searchKeyword == null}">
						<a href="/Groupware/EDL/list.gw?pageNum=${pageCount.pageNum-1 }">
							◀ 이전 </a>
					</c:if> <c:if
						test="${pageCount.pageNum != pageCount.totalPageCnt && pageCount.searchKeyword == null}">
						<a href="/Groupware/EDL/list.gw?pageNum=${pageCount.pageNum+1 }">
							다음 ▶</a>
					</c:if></td>
				<td class="fullsize"><c:if test="${user_position == '관리자'}">
						<button type="button" id="btn_upload_pdf" class="btn btn-primary"
							style="float: right" onclick="javascript:$('#pop_up').show();">문서등록</button>
					</c:if></td>
			</tr>
		</table>
		<form action="/Groupware/EDL/search.gw" name="frm_search"
			id="frm_search" method="post">
			<div style="margin: 0 auto; width: 260px; height: 50px">
				<input type="text" class="form-control"
					style="width: 200px; float: left" name="title" id="title"
					placeholder="검색할 제목을 입력하세요."><input type="submit"
					class="btn btn-primary" style="margin-left: 5px;" value="검색">
			</div>
		</form>
	</c:if>
</body>
</html>