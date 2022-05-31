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
		<table style="margin: 0 auto; margin-top: 10px" class="table">
			<tr>
				<th colspan="2" style="text-align: center"><h2>고객사 정보</h2></th>
			</tr>
            <tr style="height: 20px">
				<td style="text-align: center">거래일시:</td>
				<td width="75%">${bro.partner_contractdate}</td>
			</tr>
			<tr style="height: 20px">
				<td style="text-align: center">업체명:</td>
				<td width="75%">${bro.partner_companyname}</td>
			</tr>
			<tr style="height: 20px">
				<td style="text-align: center">담당자:</td>
				<td width="75%">${bro.partner_reprecent}</td>
			</tr>
			<tr style="height: 20px">
				<td style="text-align: center">회사연락처:</td>
				<td width="75%">${bro.partner_contact}</td>
			</tr>
			<tr style="height: 20px">
				<td style="text-align: center">회사주소:</td>
				<td width="75%">${bro.partner_address}</td>
			</tr>
			<tr style="height: 20px">
				<td style="text-align: center">거래품목:</td>
				<td width="75%">${bro.partner_tradeitem}</td>
			</tr>
			
			<tr>
				<td colspan="2"><center><img src="${pageContext.request.contextPath}/img/${bro.partner_image1}" width='500' height="200"></center></td>
			</tr>
			<tr>

				<td colspan="2"><input type="button" value="취소"
					class="btn btn-default" style="float: right"
					onclick="document.location.href='/Groupware/PTM/PTMList.ma'"></td>
			</tr>
		</table>
	</div>
</body>
</html>