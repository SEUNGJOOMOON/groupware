<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<html>
<head>
<title>글수정</title>
<link href="style.css" rel="stylesheet" type="text/css">
<script src="script.js"></script>
</head>


<br>
<form method="post" name="writeform"
	action="../board/boardupdatepro.bo?pageNum=1">
	<table border="1" class="table">
		<tr>
			<th colspan="2" style="text-align: center"><h1>공지사항</h1></th>

		</tr>
		<tr>
		<tr>
			<td width="70" align="center">사원번호</td>
			<td align="left" width="330"><span>${br.board_writerid}</span> <input
				type="hidden" name="board_num" value="${br.board_num}"></td>
		</tr>
		<tr>
			<td width="70" align="center">이름</td>
			<td align="left" width="330"><span>${br.board_writer}</span></td>
		</tr>
		<tr>
			<td width="70" align="center">제목</td>
			<td align="left" width="330"><input type="text" size="40"
				maxlength="30" name="board_title" value="${br.board_title}">
			</td>
		</tr>
		<tr>
			<td width="70" align="center">내 용</td>
			<td align="left" width="330"><textarea name="board_content"
					rows="17" cols="70">${br.board_content}</textarea></td>
		</tr>
		<tr>
			<td colspan=2 align="center"><input type="submit"
				class="btn btn-primary" value="글수정"> <input type="reset"
				class="btn btn-primary" value="다시작성"> <input type="button"
				class="btn btn-primary" value="목록보기"
				onclick="document.location.href='../board/boardList.bo?pageNum=1'">
			</td>
		</tr>
	</table>
</form>
</html>

