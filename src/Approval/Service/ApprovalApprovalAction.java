package Approval.Service;

import java.io.StringReader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

import Approval.DTO.ApprovalDocumentBean;
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
public class ApprovalApprovalAction implements ApprovalRequestPRoInter {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		int docNum = Integer.parseInt(request.getParameter("docNum"));
		
		ApprovalDBBean db = ApprovalDBBean.getInstance();
		ApprovalDocumentBean document = db.getViewDocument(docNum);
		int resultcnt = db.UpdateDocument(docNum, makeApprovalLineInfor(document));
		
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
		
		
		String nextApprovalEmpNo = "";
		String nextApprovalEmpName = "";
		String progessLineXml = "";//현재 결재라인정보 후에 구현
		int totalApprovalCount = Document.getDocTotalApprovalCount();
		int currentApprovalCount = Document.getDocCurrentApprovalCount()+1;
		int index = 0;
		try {
			t_dbf = DocumentBuilderFactory.newInstance();
			t_db = t_dbf.newDocumentBuilder();
			t_is = new InputSource();
			t_is.setCharacterStream(new StringReader(approvalLineXml));
			t_doc = t_db.parse(t_is);
			Element root = t_doc.getDocumentElement();
			
			int length = root.getElementsByTagName("approvalinfor").getLength();
            for(int i=0; i<length; i++) {
            	nodeLine = root.getElementsByTagName("approvalinfor").item(i);
            	nodeEmpNo = ((Element) nodeLine).getElementsByTagName("lineempno").item(0);
            	empNo = nodeEmpNo.getTextContent();
            	nodeEmpName = ((Element) nodeLine).getElementsByTagName("lineempname").item(0);
            	empName = nodeEmpName.getTextContent();
            	nodeApprovalKind = ((Element) nodeLine).getElementsByTagName("lineapprovaltype").item(0);
            	approvalKind = nodeApprovalKind.getTextContent();
            	if(approvalKind.equals("approval")){
            		if(index==currentApprovalCount){
            			nextApprovalEmpNo = empNo;
                		nextApprovalEmpName = empName;
            		}
            		index++;
            	}
            }
            if(currentApprovalCount==Document.getDocTotalApprovalCount()){
            	Document.setDocState("finished");//마지막결재자 승인시 결재상태 종료
            	nextApprovalEmpNo = "finished";
        		nextApprovalEmpName = "finished";
            }else{
            	Document.setDocState("Approval");
            }

		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			Document.setDocNextApprovalEmpno(nextApprovalEmpNo);
			Document.setDocCurrentApprovalCount(currentApprovalCount);
			//Document.setDocApprovalProgessline(progessLineXml);
		}

		return Document;
	}
}
