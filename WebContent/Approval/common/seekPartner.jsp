<%@ page language="java" contentType="text/xml; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="Approval.DTO.ApprovalPartnerBean"%>
<%@ page import="Approval.DAO.ApprovalDBBean"%>
<%@ page import="java.util.*"%>
<%
	request.setCharacterEncoding("utf-8");
	String companyName = request.getParameter("seekPartnerName");
	List<ApprovalPartnerBean> PartnerList = ApprovalDBBean.getInstance().getPartnerList(companyName);

	out.print("<partnerlist>");

	for (ApprovalPartnerBean partner : PartnerList) {
		out.print("<partner>");
		out.print("<partnercompanyname>" + partner.getPartnerCompanyName() + "</partnercompanyname>");
		out.print("<partneraddress>" + partner.getPartnerAddress() + "</partneraddress>");
		out.print("<partnerrepresentative>" + partner.getPartnerRepresentative() + "</partnerrepresentative>");
		out.print("</partner>");
	}
	out.print("</partnerlist>");
%>