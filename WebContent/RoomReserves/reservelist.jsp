<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="RSR.DAO.RSRDBBean"%>
<%@ page import="RSR.DTO.RSRDataBean"%>
<%@ page import="java.util.List"%>
<html>
<head>
<script type='text/javascript'
	src='http://arshaw.com/js/fullcalendar-1.6.3/jquery/jquery-1.10.2.min.js'></script>
<script type='text/javascript'
	src='http://arshaw.com/js/fullcalendar-1.6.3/jquery/jquery-ui-1.10.3.custom.min.js'></script>
<script type='text/javascript'
	src='http://arshaw.com/js/fullcalendar-1.6.3/fullcalendar/fullcalendar.min.js'></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script type='text/javascript'>
</script>
</head>
<body>
	<b>예약목록(예약 내역:${count})</b>
	<div>
			<table style="width: 100%" class="table">
				<colgroup>
					<col style="width: 10%">
					<col style="width: 18%">
					<col style="width: 18%">
					<col style="width: 18%">
					<col style="width: 36%">
				</colgroup>
				<tr height="10%">
					<th colspan="5" style="text-align: center">예약 조회</th>
				</tr>
				<tr>
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
				<c:if test="${count >0 }">
					<tr height="5%">
						<td style="text-align: center">예약자</td>
						<td style="text-align: center">예약날짜</td>
						<td style="text-align: center">회의시간</td>
						<td style="text-align: center">회의실</td>
						<td style="text-align: center">삭제</td>
					</tr>
				<c:if test = "${search == null }">
					<c:forEach var="list" items="${RSRList}">
						<tr height="20%">
							<td style="text-align: center">${list.reserve_name}</td>
							<td style="text-align: center">${list.reserve_date}</td>
							<td style="text-align: center">${list.reserve_time}</td>
							<td style="text-align: center">${list.reserve_room}</td>
							<td>
								<form method="post" action="/Groupware/RoomReserves/RSRDeleteAction.rs" onsubmit="return ">
									<input type="hidden" name="reserve_listnum" value = "${list.reserve_listnum}" />
									<input type="text" name="checkcode" style="width:75%"placeholder="사원번호를 입력하세요." id="checkcode">
									<input type="submit" id="delete" class="btn btn-primary" name="delete" value="삭제">
								</form>
							</td>
						</tr>
					</c:forEach>
				</c:if>
				<c:if test = "${search !=null }">
				<c:forEach var="list" items="${RSRList}">
						<tr height="20%">
							<td style="text-align: center">${list.reserve_name}</td>
							<td style="text-align: center">${list.reserve_date}</td>
							<td style="text-align: center">${list.reserve_time}</td>
							<td style="text-align: center">${list.reserve_room}</td>
							<td>
									<form method="post" action="/Groupware/RoomReserves/RSRDeleteAction.rs" onsubmit="return ">
									<input type="hidden" name="reserve_listnum" value = "${list.reserve_listnum}" />
									<input type="text" name="checkcode" style="width:75%"placeholder="사원번호를 입력하세요." id="checkcode">
									<input type="submit" id="delete" class="btn btn-primary" name="delete" value="삭제">
									</form>
									</td>
						</tr>
					</c:forEach>
				</c:if>
				<c:if test="${search == null}">
				<tr><td colspan="5" style="text-align: center">
					<c:set var="pageCount" value="${count / pageSize + ( count % pageSize == 0 ? 0 : 1)}" />
					<c:set var="pageBlock" value="${10}" />
					<fmt:parseNumber var="result" value="${currentPage / 10}" integerOnly="true" />
					<c:set var="startPage" value="${result * 10 + 1}" />
					<c:set var="endPage" value="${startPage + pageBlock-1}" />
					<c:if test="${endPage > pageCount}">
						<c:set var="endPage" value="${pageCount}" />
					</c:if>
					<c:if test="${startPage > 10}">
						<a href="/Groupware/RoomReserves/RSRListAction.rs?pageNum=${startPage - 10 }">[이전]</a>
					</c:if>
					<c:forEach var="i" begin="${startPage}" end="${endPage}">
						<a href="/Groupware/RoomReserves/RSRListAction.rs?pageNum=${i}">[${i}]</a>
					</c:forEach>
					<c:if test="${endPage < pageCount}">
						<a href="/Groupware/RoomReserves/RSRListAction.rs?pageNum=${startPage + 10}">[다음]</a>
					</c:if>
					</c:if>
				<c:if test="${search != null}">
				<tr>
				<td style="text-align: center" colspan="5">
				<c:set var="pageCount" value="${count / pageSize + ( count % pageSize == 0 ? 0 : 1)}" />
				<c:set var="pageBlock" value="${10}" />
				<fmt:parseNumber var="result" value="${currentPage / 10}" integerOnly="true" />
				<c:set var="startPage" value="${result * 10 + 1}" />
				<c:set var="endPage" value="${startPage + pageBlock-1}" />
				<c:if test="${endPage > pageCount}">
				<c:set var="endPage" value="${pageCount}" />
				</c:if>
				<c:if test="${startPage > 10}">
				<a href="/Groupware/RoomReserves/RSRSearchlist.rs?pageNum=${startPage - 10 }&search=${search}&searchn=${searchn}">[이전]</a>
				</c:if>
				<c:forEach var="i" begin="${startPage}" end="${endPage}">
				<a href="/Groupware/RoomReserves/RSRSearchlist.rs?pageNum=${i}&search=${search}&searchn=${searchn}">[${i}]</a>
				</c:forEach>				
				<c:if test="${endPage < pageCount}">
				<a href="/Groupware/RoomReserves/RSRSearchlist.rs?pageNum=${startPage + 10}&search=${search}&searchn=${searchn}">[다음]</a>
				</c:if>
				</td>
				</tr>
				</c:if>
				</c:if>
				</table>
		<form action="/Groupware/RoomReserves/RSRSearchAction.rs" name="rsr_search" id="rsr_search" method="post">
			<table style="width: 100%" class="table">
				<tr>
					<td colspan="5" style="text-align: center">
						<select name="searchn">
								<option value="reserve_date">예약날짜</option>
								<option value="reserve_time">회의시간</option>
								<option value="reserve_room">회의실</option>
						</select>
						<input type="text" name="search" colspan="5" style="text-align: center" placeholder="검색">
						<input type="submit" class="btn btn-primary" id="OK" name="OK" value="검색" colspan="5" style="text-align: center">
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>