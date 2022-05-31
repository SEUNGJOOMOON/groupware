<%@ page language="java" contentType="text/xml; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%
	System.out.println("결과 :::");
	request.setCharacterEncoding("utf-8");
	SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");

	Date date1 = sd.parse(request.getParameter("date1"));
	Date date2 = sd.parse(request.getParameter("date1"));
	Date selectedDate = sd.parse(request.getParameter("selectedDate"));

	int result;
	int result2;
	result = selectedDate.compareTo(date1);
	result2 = selectedDate.compareTo(date2);

	if (result > 0 && result2 < 0) {
		System.out.println("기준일에 포함");
	} else {
		System.out.println("기준일에 포함되지 않음");
	}
%>