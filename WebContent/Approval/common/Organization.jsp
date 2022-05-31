<%@ page language="java" contentType="text/xml; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="Approval.DTO.ApprovalUserBean"%>
<%@ page import="Approval.DTO.ApprovalDeptBean"%>
<%@ page import="Approval.DAO.ApprovalDBBean"%>
<%@ page import="java.util.*"%>
<%
	ApprovalDBBean DB = ApprovalDBBean.getInstance();
	List<ApprovalDeptBean> DeptList = DB.getDeptList();
	List<ApprovalUserBean> UserList = null;
	out.println("<Organization>");
	for (ApprovalDeptBean dept : DeptList) {
		out.println("<Dept>");
		UserList = DB.getOrganizationList(dept.getDeptCode());
		out.println("<DeptName>" + dept.getDeptName() + "</DeptName>");
		for (ApprovalUserBean user : UserList) {
			out.println("<Emp>");
			out.println("<EmpNo>" + user.getEmpNo() + "</EmpNo>");
			out.println("<EmpName>" + user.getEmpName() + "</EmpName>");
			out.println("<EmpDeptCode>" + user.getEmpdeptCode() + "</EmpDeptCode>");
			out.println("<EmpPosition>" + user.getEmpPosition() + "</EmpPosition>");
			out.println("<EmpAddress>" + user.getEmpAddress() + "</EmpAddress>");
			out.println("<EmpContact>" + user.getEmpContact() + "</EmpContact>");
			out.println("<EmpProfileimg>" + user.getEmpProfileimg() + "</EmpProfileimg>");
			out.println("<EmpHiredate>" + user.getEmpHiredate() + "</EmpHiredate>");
			out.println("</Emp>");
		}
		out.println("</Dept>");
	}
	out.println("</Organization>");
%>