<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>�����������</title>
<link href="style.css" rel="stylesheet" type="text/css">

<script>
function deleteSave() {
	if(document.delForm.passwd.value=='') {
		alert("��й�ȣ�� �Է��Ͻʽÿ�.");
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
     <td align=center >��й�ȣ :  
       <input type="password" name="passwd" size="10">
   <input type="hidden" name="num" value="${num}"></td>
</tr>
<tr height="30">
    <td align="center">
      <input type="submit" value="�ۻ���" class="btn btn-primary-sm">
      <input type="button" value="�۸��" class="btn btn-primary-sm"
       onclick="document.location.href='/Groupware/HRmag/getList.hm?pageNum=${pageNum}'">    
   </td>
</tr> 
</form>
</body>
</html>