package Approval.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Approval.DTO.ApprovalDocuInforBean;
import Approval.DTO.ApprovalDocumentBean;
import Approval.DAO.ApprovalDBBean;
import java.util.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class ApprovalDraftAction implements ApprovalRequestPRoInter {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		ApprovalDocumentBean Document = new ApprovalDocumentBean();
		Document.setDocTitle(request.getParameter("approvalTitle"));
		Document.setDocContent(request.getParameter("approvalContentXml"));
		System.out.println(request.getParameter("approvalContentXml"));
		Document.setDocEmpno(session.getAttribute("user_empno").toString());
		Document.setDocEmpname(session.getAttribute("user_name").toString());
		Document.setDocType(request.getParameter("approvaldocumentType"));
		Document.setDocApprovalline(request.getParameter("approvalLineXml"));
		Document.setDocDocunum(request.getParameter("approvaldocumentNumber"));
		Document.setDocFileName(request.getParameter("approvalfilename"));
		Document.setDocFileurl(request.getParameter("approvalfileurl"));
		Document.setDocCurrentApprovalCount(0);
		Document.setDocState("Draft");
		ApprovalDBBean db = ApprovalDBBean.getInstance();
		int resultcnt = db.DraftDocument(makeApprovalLineInfor(Document));

		return "/Approval/common/approvalresult.jsp?resultcnt=" + resultcnt;
	}

	public ApprovalDocumentBean makeApprovalLineInfor(ApprovalDocumentBean Document) {
		String approvalLineXml = Document.getDocApprovalline();

		DocumentBuilderFactory t_dbf = null;
		DocumentBuilder t_db = null;
		Document t_doc = null;
		InputSource t_is = new InputSource();

		Node nodeLine = null;
		Node nodeEmpNo = null;
		Node nodeEmpName = null;
		Node nodeApprovalKind = null;

		String empNo = "";
		String empName = "";
		String approvalKind = "";

		int totalApprovalCount = 0;
		String nextApprovalEmpNo = "";
		String nextApprovalEmpName = "";
		String progessLineXml = "";// 현재 결재라인정보 후에 구현
		List<String> references = new ArrayList();

		try {
			t_dbf = DocumentBuilderFactory.newInstance();
			t_db = t_dbf.newDocumentBuilder();
			t_is = new InputSource();
			t_is.setCharacterStream(new StringReader(approvalLineXml));
			t_doc = t_db.parse(t_is);
			Element root = t_doc.getDocumentElement();

			int length = root.getElementsByTagName("approvalinfor").getLength();
			for (int i = 0; i < length; i++) {
				nodeLine = root.getElementsByTagName("approvalinfor").item(i);
				nodeEmpNo = ((Element) nodeLine).getElementsByTagName("lineempno").item(0);
				empNo = nodeEmpNo.getTextContent();
				nodeEmpName = ((Element) nodeLine).getElementsByTagName("lineempname").item(0);
				empName = nodeEmpName.getTextContent();
				nodeApprovalKind = ((Element) nodeLine).getElementsByTagName("lineapprovaltype").item(0);
				approvalKind = nodeApprovalKind.getTextContent();
				if (approvalKind.equals("approval")) {
					totalApprovalCount++;
					if (i == 1) {
						nextApprovalEmpNo = empNo;
						nextApprovalEmpName = empName;
					}
				} else if (approvalKind.equals("Draft")) {
					progessLineXml = "<progressline>" + "<progressinfor>" + "<progressempno>" + empNo
							+ "</progressempno>" + "<progressempname>" + empName + "</progressempname>"
							+ "<progresstype>Draft<progresstype>" + "</progressinfor>" + "</progressline>";
				} else if (approvalKind.equals("reference")) {
					references.add(empNo + ";");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Document.setDocTotalApprovalCount(totalApprovalCount);
			Document.setDocNextApprovalEmpno(nextApprovalEmpNo);
			Document.setDocApprovalProgessline(progessLineXml);
			Document.setReferences(references);
		}

		return Document;
	}

}
