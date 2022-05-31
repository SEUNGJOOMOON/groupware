<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>결재문서보기</title>
<style>
.table th {
	background-color: #eaeaea;
}
</style>
</head>
<body>
	<span style="float: right">[현재 페이지 : ${pageCount.pageNum }/전체
		페이지 : ${pageCount.totalPageCnt }]</span>
	<table class="table">
		<colgroup>
			<col style="width: 15%" class="fullsize">
			<col style="width: 40%">
			<col style="width: 15%">
			<col style="width: 15%" class="fullsize">
			<col style="width: 15%">
		</colgroup>
		<caption>
			<span style="font-size: 15pt; margin-left: 30px"><b><c:choose>
						<c:when test="${param.listtype == 'mylist'}">내문서함</c:when>
						<c:when test="${param.listtype == 'unfinished'}">미결함</c:when>
						<c:when test="${param.listtype == 'finished'}">완료함</c:when>
						<c:when test="${param.listtype == 'reference'}">참조함</c:when>
					</c:choose> </b></span>
		</caption>
		<tr>
			<th style="text-align: center" class="fullsize">종류</th>
			<th style="text-align: center">제목</th>
			<th style="text-align: center">기안자</th>
			<th style="text-align: center" class="fullsize">날짜</th>
			<th style="text-align: center">처리상태</th>
		</tr>

		<c:if test="${fn:length(documentListBean.documentList) != 0}">
			<c:forEach var="list" items="${documentListBean.documentList}">
				<!-- 내문서함 -->
				<tr>
					<td style="text-align: center" class="fullsize">${list.docInfor.appdocName}</td>
					<td style="text-align: center"><a
						href="javascript:open('/Groupware/Approval/View.ap?docNum=${list.docNum}&listType=${listType}', '',
					'width=900, height=700, status=1')">${list.docTitle}</a></td>
					<td style="text-align: center">${list.docEmpname}</td>
					<td style="text-align: center" class="fullsize">${list.docDraftDate}</td>
					<c:if test="${list.docState=='Draft'}">
						<td style="text-align: center">기안</td>
					</c:if>
					<c:if test="${list.docState=='finished'}">
						<td style="text-align: center">완료</td>
					</c:if>
					<c:if test="${list.docState=='Rejected'}">
						<td style="text-align: center">반려</td>
					</c:if>
					<c:if test="${list.docState=='Approval'}">
						<td style="text-align: center">처리중</td>
					</c:if>
				</tr>
			</c:forEach>
		</c:if>
		<c:if test="${fn:length(documentListBean.documentList) == 0}">
			<tr class="fullsize">
				<td colspan="5" style="text-align: center">현재 결재문서가 없습니다.</td>
			</tr>
		</c:if>
		<tr>
			<td style="text-align: center" colspan="5"><c:if
					test="${pageCount.pageNum != 1}">
					<a
						href="/Groupware/Approval/List.ap?pageNum=${pageCount.pageNum-1 }&listtype=${param.listtype }">
						◀ 이전 </a>
				</c:if> <c:if test="${pageCount.pageNum != pageCount.totalPageCnt}">
					<a
						href="/Groupware/Approval/List.ap?pageNum=${pageCount.pageNum+1 }&listtype=${param.listtype }">
						다음 ▶</a>
				</c:if> <input type="button" value="메인으로" class="btn btn-primary fullsize"
				style="float: right"
				onclick="location.href='/Groupware/Approval/main.jsp'"></td>
		</tr>
	</table>
</body>
</html>