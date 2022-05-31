<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>사원정보삭제</title>
<link href="style.css" rel="stylesheet" type="text/css">

<script>
function deleteSave() {
	if(document.delForm.passwd.value=='') {
		alert("비밀번호를 입력하십시오.");
		document.delForm.passwd.focus();
		return false;
	}
}

</script>
</head>
<body>
<form method="post" name="delForm" action="/Groupware/HRmag/delete.hm?pageNum=${pageNum}" onsubmit="return writeSave()">
<table align="center" width="250" class="table table-striped">

  <tr height="30">
     <td align=center >비밀번호 :  
       <input type="password" name="passwd" size="10">
   <input type="hidden" name="num" value="${num}"></td>
</tr>
<tr height="30">
    <td align="center">
      <input type="submit" value="글삭제" class="btn btn-primary-sm">
      <input type="button" value="글목록" class="btn btn-primary-sm"
       onclick="document.location.href='/Groupware/HRmag/getList.hm?pageNum=${pageNum}'">    
   </td>
</tr> 
</form>
</body>
</html>