<%@ page language="java" contentType="text/xml; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="Approval.DTO.ApprovalUserBean"%>
<%@ page import="Approval.DAO.ApprovalDBBean"%>
<%@ page import="java.util.*"%>
<%
	request.setCharacterEncoding("utf-8");
	String empNo = request.getParameter("empno");
	ApprovalDBBean DB = ApprovalDBBean.getInstance();
	ApprovalUserBean User = DB.getUserDetail(empNo);
	out.println("<User>");
	out.println("<EmpNo>" + User.getEmpNo() + "</EmpNo>");
	out.println("<EmpName>" + User.getEmpName() + "</EmpName>");
	out.println("<EmpDeptCode>" + User.getEmpdeptCode() + "</EmpDeptCode>");
	out.println("<EmpPosition>" + User.getEmpPosition() + "</EmpPosition>");
	out.println("<EmpAddress>" + User.getEmpAddress() + "</EmpAddress>");
	out.println("<EmpContract>" + User.getEmpContact() + "</EmpContract>");
	out.println("<EmpProfileimg>" + User.getEmpProfileimg() + "</EmpProfileimg>");
	out.println("<EmpHiredate>" + User.getEmpHiredate() + "</EmpHiredate>");
	out.println("</User>");
%>