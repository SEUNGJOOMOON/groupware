package Approval.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Approval.DTO.ApprocalDocumentListBean;
import Approval.DTO.ApprovalPageBean;
import Approval.DTO.ApprovalDocumentBean;
import Approval.DAO.ApprovalDBBean;
import java.util.*;

public class ApprovalListAction implements ApprovalRequestPRoInter {

	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		String listType = request.getParameter("listtype");
		String empNo = request.getSession().getAttribute("user_empno").toString();
		
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null) {
			pageNum = "1";
		}
		
		
		ApprovalDBBean db = ApprovalDBBean.getInstance();
		ApprovalPageBean pageCount = db.getListCnt(empNo, listType);
		pageCount.setPageNum(Integer.parseInt(pageNum));
		pageCount.setTotalPageCnt((int) Math.ceil(pageCount.getTotalCnt() / 10.0));
		if(pageCount.getTotalPageCnt() == 0){
			pageCount.setTotalPageCnt(1);
		}
		List<ApprovalDocumentBean> documentList = null;
		ApprocalDocumentListBean documentListBean = new ApprocalDocumentListBean();
		
		if (listType.equals("unfinished")) {// 진행함
			documentList = db.getUnfinishedList(empNo, pageNum);
			documentListBean.setDocumentList(documentList);
			documentListBean.setListType("unfinished");
		} else if (listType.equals("finished")) {// 완료함
			documentList = db.getFinishedList(empNo, pageNum);
			documentListBean.setDocumentList(documentList);
			documentListBean.setListType("finished");
		} else if (listType.equals("reference")) {// 참조함
			documentList = db.getReferencedList(empNo, pageNum);
			documentListBean.setDocumentList(documentList);
			documentListBean.setListType("reference");
		} else if (listType.equals("mylist")) {// 내문서함
			documentList = db.getAllList(empNo, pageNum);
			documentListBean.setDocumentList(documentList);
			documentListBean.setListType("mylist");
		}
		request.setAttribute("documentListBean", documentListBean);
		request.setAttribute("listType", listType);
		request.setAttribute("pageCount", pageCount);
		return "/Approval/list.jsp";
	}

}
