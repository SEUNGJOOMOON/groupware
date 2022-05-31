<%@ page contentType="text/html; charset=utf-8"%>

<html>
<head>
<title>Insert title here</title>
</head>
<body>
	<%
		response.sendRedirect("/Groupware/HR/select.imf?emp_no=" + request.getSession().getAttribute("user_empno"));
	%>
</body>
</html>