<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
		<title>고객List</title>
	</head>
	<body>
		<div style="width: 100%; margin: 0 auto">
			<form name="main" method="post" action="/Groupware/PTM/PTMSearch.ma">
				<table style="margin: 0 auto; margin-top: 10px" class="table">
					<tr>
						<th colspan="4" style="text-align: center">
							<h2>고객사</h2>
						</th>
					</tr>
					<c:if test="${count == 0}">
						<tr>
							<td colspan="4" style="text-align: center">
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
						<td style="text-align: center">거래일시</td>
						<td style="text-align: center">업체명</td>
						<td style="text-align: center">담당자</td>
						<td style="text-align: center">회사연락처</td>
							
							
						</tr>
						<c:if test="${find==null }">
						<c:forEach var="list" items="${dblist}">
							<tr style="height: 20px">
							  <td style="text-align: center">${list.partner_contractdate}</td>
							   <td style="text-align: center"><a href="/Groupware/PTM/PTMView.ma?partner_companyname=${list.partner_companyname}&partner_companyname=${requestScope.partner_companyname}">${list.partner_companyname}</a></td>						
								<td style="text-align: center">${list.partner_reprecent}</td>
								<td style="text-align: center">${list.partner_contact}</td>
								
							</tr>
						</c:forEach>
						</c:if>
						<c:if test="${find!=null }">
							<c:forEach var="list" items="${dblist}">
							<tr style="height: 20px">
							   <td style="text-align: center">${list.partner_contractdate}</td>
							   <td style="text-align: center"><a href="/Groupware/PTM/PTMView.ma?partner_companyname=${list.partner_companyname}&partner_companyname=${requestScope.partner_companyname}">${list.partner_companyname}</a></td>
								<td style="text-align: center">${list.partner_reprecent}</td>
								<td style="text-align: center">${list.partner_contact}</td>
				
								
							</tr>
						</c:forEach>
						</c:if>
						
						
						<c:if test="${find==null }">
						<tr>
							<td style="text-align: center" colspan="4">
								<c:set var="pageCount" value="${count / pageSize + ( count % pageSize == 0 ? 0 : 1)}" />
								<c:set var="pageBlock" value="${10}" />
								<fmt:parseNumber var="result" value="${currentPage / 10}" integerOnly="true" />
								<c:set var="startPage" value="${result * 10 + 1}" />
								<c:set var="endPage" value="${startPage + pageBlock-1}" />
								<c:if test="${endPage > pageCount}">
									<c:set var="endPage" value="${pageCount}" />
								</c:if>
								<c:if test="${startPage > 10}">
									<a href="/Groupware/PTM/PTMList.ma?pageNum=${startPage - 10 }">[이전]</a>
								</c:if>
							
								<c:forEach var="i" begin="${startPage}" end="${endPage}">
									<a href="/Groupware/PTM/PTMList.ma?pageNum=${i}">[${i}]</a>
								</c:forEach>
								
								<c:if test="${endPage < pageCount}">
									<a href="/Groupware/PTM/PTMList.ma?pageNum=${startPage + 10}">[다음]</a>
								</c:if>
							</td>
						</tr>
						</c:if>
						<c:if test="${find!=null }">
							<tr>
							<td style="text-align: center" colspan="4">
								<c:set var="pageCount" value="${count / pageSize + ( count % pageSize == 0 ? 0 : 1)}" />
								<c:set var="pageBlock" value="${10}" />
								<fmt:parseNumber var="result" value="${currentPage / 10}" integerOnly="true" />
								<c:set var="startPage" value="${result * 10 + 1}" />
								<c:set var="endPage" value="${startPage + pageBlock-1}" />
								<c:if test="${endPage > pageCount}">
									<c:set var="endPage" value="${pageCount}" />
								</c:if>
								<c:if test="${startPage > 10}">
									<a href="/Groupware/PTM/PTMSearchlist.ma?pageNum=${startPage - 10 }&partner_companyname=${find}">[이전]</a>
								</c:if>
							
								<c:forEach var="i" begin="${startPage}" end="${endPage}">
									<a href="/Groupware/PTM/PTMSearchlist.ma?pageNum=${i}&partner_companyname=${find}">[${i}]</a>
								</c:forEach>
								
								<c:if test="${endPage < pageCount}">
									<a href="/Groupware/PTM/PTMSearchlist.ma?pageNum=${startPage + 10}&partner_companyname=${find}">[다음]</a>
								</c:if>
							</td>
						</tr>
						</c:if>
						</c:if>
					
					<tr style="height: 20px">
						<td colspan="5" style="text-align: center">
							<input type="text" name="find" placeholder="업체명" style="text-align: center">
							<input type="button" value="입력"class="btn btn-primary" onclick="document.location.href='/Groupware/PartnerManage/Enrollmentform.jsp'" style="float: right">
							<input type="submit" value="검색" style="float: right" class="btn btn-primary">
						</td>
					</tr>
				</table>
			</form>
		</div>
	</body>
</html>