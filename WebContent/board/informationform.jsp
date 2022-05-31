<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>고객사 정보</title>
</head>
<body> 
	<div style="width: 700px; margin: 0 auto">
	<form name="info" method="post" action="/Groupware/board/boarddelete.bo?board_num=${bro.board_num}">
		<table style="margin: 0 auto; margin-top: 10px" class="table table-bordered">
			<tr>
				<th colspan="2" style="text-align: center"><h2>공지사항</h2></th>
			
			</tr>
			<tr style="height: 20px">
				<td style="text-align: center"width="12%">작성일:</td>
				<td width="75%">${bro.board_date}</td>
			</tr>
			<tr style="height: 20px">
				<td style="text-align: center">사원번호:</td>
				<td width="75%">${bro.board_writerid}</td>
			</tr>
			<tr style="height: 20px">
				<td style="text-align: center">이름:</td>
				<td width="75%">${bro.board_writer}</td>
			</tr>
			<tr style="height: 20px">
				<td style="text-align: center">제목:</td>
				<td width="75%">${bro.board_title}</td>
			</tr>
			<tr style="height: 300px">
				<td style="text-align: center">내용:</td>
				<td width="75%">${bro.board_content}</td>
			</tr>
			<tr style="height: 20px">
				<td style="text-align: center">조회수:</td>
				<td width="75%">${bro.board_hit}</td>
			</tr>
</table>
			<tr>

				<td colspan="2">
				<input type="button" value="취소"class="btn btn-primary" style="float: right"
					onclick="document.location.href='/Groupware/board/boardList.bo'"><c:if test="${user_position == '관리자'}">
					<input type="submit" value="삭제" class="btn btn-primary" style="float: right">
					<input type="button" value="글수정" class="btn btn-primary" style="float: right"
					onclick="document.location.href='/Groupware/board/boardupdate.bo?board_num=${bro.board_num}'"></c:if>
					</td>
			</tr>
	
		</form>
	</div>
</body>
</html>