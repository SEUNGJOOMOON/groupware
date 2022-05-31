package Approval.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Approval.DAO.ApprovalDBBean;
import Approval.DTO.ApprovalDocumentBean;

public class ApprovalRejectAction implements ApprovalRequestPRoInter{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		int docNum = Integer.parseInt(request.getParameter("docNum"));
		
		ApprovalDBBean db = ApprovalDBBean.getInstance();
		ApprovalDocumentBean document = db.getViewDocument(docNum);
		document.setDocState("Rejected");
		document.setDocNextApprovalEmpno("Rejected");
		int resultcnt = db.UpdateDocument(docNum, document);
		
		return "/Approval/common/approvalresult.jsp?resultcnt=" + resultcnt;
	}

}
