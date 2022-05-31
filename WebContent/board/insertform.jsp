<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>게시글쓰기</title>
</head>
<body>
	<form method="post" action="../board/boardInsert.bo" name="insert"
		onsubmit="return checked();">
		<div>
			<table border="1" class="table">
				<tr>
					<th colspan="2" style="text-align: center"><h1>공지사항</h1></th>
				</tr>
				<tr>
					<th>사원번호</th>
					<td><span>${user_empno}</span><input type="hidden"
						name="board_writerid" value="${user_empno}"></td>
				</tr>
				<tr>
					<th>이름</th>
					<td><span>${user_name}</span><input type="hidden"
						name="board_writer" value="${user_name}"></td>
				</tr>
				<tr>
					<th>제목</th>
					<td><input type="text" name="board_title"
						style="width: 50%; height: 50%"></td>
				</tr>
				<tr>
					<th>내용</th>
					<td><textarea name="board_content" rows="17" cols="80"></textarea></td>
				</tr>

				<tr>
					<td colspan="2" align="center"><input type="submit" value="저장"
						class="btn btn-primary"> <input type="reset" value="다시작성"
						class="btn btn-primary"></td>
				</tr>
			</table>
		</div>
	</form>
</body>
</html>