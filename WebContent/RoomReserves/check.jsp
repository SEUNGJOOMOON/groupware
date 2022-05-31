<%@ page contentType="text/xml; charset=UTF-8"%>
<%@page trimDirectiveWhitespaces="true"%>
<?xml version="1.0" encoding="UTF-8"?>
<%
	String cal1 = request.getParameter("#datepicker1");
	String tim1 = request.getParameter("reserve_room");
	String inf1 = request.getParameter("reserve_time");
	String nam1 = request.getParameter("#reserve_name");
	String num1 = request.getParameter("#reserve_num");
%>
<reserveInfor> 
<date><%=cal1%></date> 
<time><%=tim1%></time>
<info><%=inf1%></info> 
<name><%=nam1%></name>
<num><%=num1%></num>
</reserveInfor>
