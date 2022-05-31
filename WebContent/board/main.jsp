<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setCharacterEncoding("UTF-8");
%>

<html>
<head>
<style>
table {
	text-align: center;
}
</style>
<title>공지사항</title>
</head>
<body>

	<div style="width: 100%; margin: 0 auto">
		<form name="listf" method="post"
			action="/Groupware/board/boardSearch.bo">
			<table style="margin: 0 auto; margin-top: 10px" class="table">
				<tr>
					<th colspan="5" style="text-align: center">
						<h2>공지사항</h2>
					</th>
				</tr>
				
				<c:if test="${count == 0}">
					<tr>
						<td colspan="6" style="text-align: center">
							<table>
								<tr>
									<td align="center">검색결과가 없습니다.</td>
								</tr>
							</table>
						</td>
					</tr>
				</c:if>

				<c:if test="${count > 0}">


					<tr>
					
						<td style="text-align: center">글번 호</td>
						<td style="text-align: center" width="45%">제 목</td>
					    <td style="text-align: center">작성일</td>
						
						<td style="text-align: center">작성자</td>
						<td style="text-align: center">조 회</td>

					</tr>
					<c:if test="${Search==null }">
						<c:forEach var="list" items="${dblist}">
							<tr style="height: 20px">


								<td style="text-align: center">${list.board_num}</td>
								<td style="text-align: center"><a
									href="/Groupware/board/boardview.bo?board_title=${list.board_title}&board_title=${requestScope.board_title}">${list.board_title}</a></td>
								<td style="text-align: center">${list.board_date}</td>
								
								<td style="text-align: center">${list.board_writer}</td>
								<td style="text-align: center">${list.board_hit}</td>
							</tr>
						</c:forEach>
					</c:if>
					<c:if test="${Search!=null }">
						<c:forEach var="list" items="${dblist}">
							<tr style="height: 20px">


								<td style="text-align: center">${list.board_num}</td>
								<td style="text-align: center"><a
									href="/Groupware/board/boardview.bo?board_title=${list.board_title}&board_title=${requestScope.board_title}">${list.board_title}</a></td>
								<td style="text-align: center">${list.board_date}</td>
								
								<td style="text-align: center">${list.board_writer}</td>
								
								<td style="text-align: center">${list.board_hit}</td>
							</tr>
						</c:forEach>
					</c:if>
					<c:if test="${Search==null }">
						<tr>
							<td style="text-align: center" colspan="5"><c:set
									var="pageCount"
									value="${count / pageSize + ( count % pageSize == 0 ? 0 : 1)}" />
								<c:set var="pageBlock" value="${10}" /> <fmt:parseNumber
									var="result" value="${currentPage / 10}" integerOnly="true" />
								<c:set var="startPage" value="${result * 10 + 1}" /> <c:set
									var="endPage" value="${startPage + pageBlock-1}" /> <c:if
									test="${endPage > pageCount}">
									<c:set var="endPage" value="${pageCount}" />
								</c:if> <c:if test="${startPage > 10}">
									<a
										href="/Groupware/board/boardList.bo?pageNum=${startPage - 10 }">[이전]</a>
								</c:if> <c:forEach var="i" begin="${startPage}" end="${endPage}">
									<a href="/Groupware/board/boardList.bo?pageNum=${i}">[${i}]</a>
								</c:forEach> <c:if test="${endPage < pageCount}">
									<a
										href="/Groupware/board/boardList.bo?pageNum=${startPage + 10}">[다음]</a>
								</c:if></td>
						</tr>


					</c:if>
					<c:if test="${Search!=null }">
						<tr>
							<td style="text-align: center" colspan="5"><c:set
									var="pageCount"
									value="${count / pageSize + ( count % pageSize == 0 ? 0 : 1)}" />
								<c:set var="pageBlock" value="${10}" /> <fmt:parseNumber
									var="result" value="${currentPage / 10}" integerOnly="true" />
								<c:set var="startPage" value="${result * 10 + 1}" /> <c:set
									var="endPage" value="${startPage + pageBlock-1}" /> <c:if
									test="${endPage > pageCount}">
									<c:set var="endPage" value="${pageCount}" />
								</c:if> <c:if test="${startPage > 10}">
									<a
										href="/Groupware/board/boardSearchlist.bo?pageNum=${startPage - 10 }&sel=${sel}&Search=${Search}">[이전]</a>
								</c:if> <c:forEach var="i" begin="${startPage}" end="${endPage}">
									<a href="/Groupware/board/boardSearchlist.bo?pageNum=${i}&sel=${sel}&Search=${Search}">[${i}]</a>
								</c:forEach> <c:if test="${endPage < pageCount}">
									<a
										href="/Groupware/board/boardSearchlist.bo?pageNum=${startPage + 10}&sel=${sel}&Search=${Search}">[다음]</a>
								</c:if></td>
						</tr>
						</c:if>
						</c:if>
						<tr style="height: 20px">
							<td colspan="5" style="text-align: center">
							
							<select name="sel">
							
						<option value="board_writer">작성자</option>
						<option value="board_title">제목</option>
							</select>
							
							
							<input type="text" name="Search"><c:if test="${user_position == '관리자'}"><input
								type="button" value="글작성" class="btn btn-primary"
								onclick="document.location.href='/Groupware/board/insertform.jsp'"
								style="float: right"></c:if><input type="submit" value="검색"
								style="float: right" class="btn btn-primary"></td>
						</tr>
			</table>
		</form>
	</div>

</body>
</html>