package Approval.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Approval.DTO.ApprovalDocumentBean;
import Approval.DAO.ApprovalDBBean;
import Approval.DTO.ApprovalDocuInforBean;

public class ApprovalViewAction implements ApprovalRequestPRoInter {

	
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		int docNum = Integer.parseInt(request.getParameter("docNum"));
		
		
		ApprovalDBBean db = ApprovalDBBean.getInstance();
		ApprovalDocumentBean documentInformation = db.getViewDocument(docNum);
		ApprovalDocuInforBean documentProperties = db.getDocumentProperty(documentInformation.getDocDocunum());
		request.setAttribute("documentInformation", documentInformation);
		
		return "/Approval/forms/read" + documentProperties.getAppdocReadUrl();
	}

}
