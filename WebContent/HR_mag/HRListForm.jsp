<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="HRmag.DTO.*"%>
<%@page import="HRmag.DAO.*"%>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<script src="http://code.jquery.com/jquery-1.11.3.min.js"></script>

<content tag="local_script">
<style>
th {
	text-align: center;
}

#search {
	margin: 10px;
}

#section {
	width: 700px;
	margin: 0 auto;
}
</style>


<script>
	function delList(url) {
		var result = confirm("삭제하시겠습니까?");
		if (result) {
			location.replace(url);
		} else {
		}
	}
</script>
</content>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>사원통합관리</title>
</head>
<body>

	<c:if test="${count==0 }">
		<table class="table table-striped">
			<tr>
				<th align="center">사 원 통 합 관 리</th>
			</tr>
			<tr>
				<td colspan="10" align="center">등록된 정보가 없습니다.</td>
			</tr>
			<tr>
				<td align="center"><a
					href="/Groupware/HR_mag/enrollmentForm.jsp">사원신규등록</a></td>
			</tr>

		</table>
	</c:if>


	<c:if test="${count>0}">
		<table id="HRList" class="table table-striped">
			<colgroup>
				<col style="width:5%"/>
				<col style="width:15%"/>
				<col style="width:10%"/>
				<col style="width:8%"/>
				<col style="width:15%"/>
				<col style="width:15%"/>
				<col style="width:10%"/>
				<col style="width:10%"/>
				<col style="width:7%"/>
				<col style="width:5%"/>
			</colgroup>
			<tr height="10%">
				<th align="left"><button type="button"
						class="btn btn-primary btn-sm"
						onclick="location.href='/Groupware/HR_mag/enrollmentForm.jsp';">
						<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
					</button></th>
				<th colspan="9" align="center">사 원 목 록</th>
			</tr>
			<tr>
				<th>사원코드</th>
				<th>사원명</th>
				<th>직급</th>
				<th>부서코드</th>
				<th>부서명</th>
				<th>입사일</th>
				<th>주소</th>
				<th>전화번호</th>
				<th>P.W</th>
				<th></th>

			</tr>
			<c:forEach var="hrList" items="${hrList }">
				<tr>
					<td align="center">${hrList.emp_no }</td>
					<td align="center"><a
						href=/Groupware/HRmag/modifyForm.hm?pageNum=${currentPage}&emp_no=${hrList.emp_no}>${hrList.emp_name }</a></td>
					<td align="center">${hrList.emp_position }</td>
					<td align="center">${hrList.dept_code }</td>
					<td align="center">${hrList.dept_name}</td>
					<td align="center">${hrList.emp_hiredate }</td>
					<td align="center">${hrList.emp_address }</td>
					<td align="center">${hrList.emp_contact }</td>
					<td align="center">${hrList.emp_password }</td>
					<td align="center" width="15"><button type="button"
							class="btn btn-primary btn-xs"
							onclick="delList('/Groupware/HRmag/delete.hm?pageNum=${currentPage}&emp_no=${hrList.emp_no}');">
							<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
						</button></td>

				</tr>
			</c:forEach>

		</table>
		</form>
	</c:if>
	<c:if test="${count>0 }">
		<table>
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
						href="/Groupware/HRmag/getList.hm?pageNum=${numPageGroup-2*pageGroupSize+1 }">[이전]</a>
				</c:if>

				<c:forEach var="i" begin="${startPage }" end="${endPage }">
					<a href="/Groupware/HRmag/getList.hm?pageNum=${i }">[${i }]</a>
				</c:forEach>

				<c:if test="${numPageGroup < pageGroupCount}">
					<a
						href="/Groupware/HRmag/getList.hm?pageNum=${numPageGroup*pageGroupSize+1}">[다음]</a>
				</c:if>
			</center>
			<form name="searchForm" method="get"
				action="/Groupware/HRmag/search.hm">

				</c:if>
</body>
</html>