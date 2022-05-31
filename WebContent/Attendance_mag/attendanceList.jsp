<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ page import="java.util.List"%>
<%@page import="HRmag.DTO.*"%>
<%@page import="HRmag.DAO.*"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<script src="http://code.jquery.com/jquery-1.11.3.min.js"></script>

<style>
th {
	text-align: center;
}

#search {
	margin: 10px;
}
</style>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>사원 근태 현황</title>
</head>
<body>
	<c:if test="${count>0 }">
		<table class="table table-striped">
			<tr>
				<th style="text-align:center">사원코드</th>
				<th style="text-align:center">사원명</th>
				<th style="text-align:center">날짜</th>
				<th style="text-align:center">부서</th>
				<th style="text-align:center">출근시간</th>
				<th style="text-align:center">퇴근시간</th>
				<th style="text-align:center">지각사유</th>
			</tr>

			<c:forEach var="attendace" items="${attendanceList }">
				<tr>
					<td align="center">${attendace.emp_no }</td>
					<td align="center">${attendace.emp_name }</td>
					<td align="center">${attendace.emp_date }</td>
					<td align="center">${attendace.dept_name}</td>
					<td align="center">${attendace.attend_time}</td>
					<td align="center">${attendace.getoff_time }</td>
					<c:if test="${attendace.late_reason!=''}">
						<td align="center">${attendace.late_reason }</td>
					</c:if>
					<c:if test="${attendace.late_reason==''}">
						<td align="center"></td>
					</c:if>
				</tr>
			</c:forEach>
		</table>
	</c:if>

	<c:if test="${count>0 }">
		<center>
			<c:set var="pageCount"
				value="${count/pageSize +(count%pageSize == 0? 0:1)}" />
			<c:set var="startPage" value="${pageGroupSize*(numPageGroup-1)+1 }" />
			<c:set var="endPage" value="${startPage+pageGroupSize-1 }" />

			<c:if test="${endPage>pageCount }">
				<c:set var="endPage" value="${pageCount }" />
			</c:if>

			<c:if test="${numPageGroup>1}">
				<a
					href="/Groupware/Attendance/attendanceList.hm?pageNum=${(numPageGroup-2)*pageGroupSize+1 }">[이전]</a>
			</c:if>

			<c:forEach var="i" begin="${startPage }" end="${endPage }">
				<a href="/Groupware/Attendance/attendanceList.hm?pageNum=${i }">[${i }]</a>
			</c:forEach>

			<c:if test="${numPageGroup < pageGroupCount}">
				<a
					href="/Groupware/Attendance/attendanceList.hm=${numPageGroup*pageGroupSize+1}">[다음]</a>
			</c:if>
		</center>
	</c:if>
</body>
</html>