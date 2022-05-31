package Approval.DTO;

import java.util.Date;
import java.util.List;

import Approval.DTO.ApprovalDocuInforBean;

public class ApprovalDocumentBean {
	private int docNum;
	private String docTitle;
	private String docApprovalline;
	private String docApprovalProgessline;
	private int docTotalApprovalCount;
	private int docCurrentApprovalCount;
	private String docNextApprovalEmpno;
	private String docEmpname;
	private String docEmpno;
	private String docContent;
	private String docFileurl;
	private String docFileName;
	private ApprovalDocuInforBean docInfor;
	private List<String> references;
	public List<String> getReferences() {
		return references;
	}
	public void setReferences(List<String> references) {
		this.references = references;
	}

	private String docState;
	private String docType;
	private String docDocunum;
	private Date docDraftDate;

	public ApprovalDocuInforBean getDocInfor() {
		return docInfor;
	}
	public void setDocInfor(ApprovalDocuInforBean docInfor) {
		this.docInfor = docInfor;
	}

	public String getDocApprovalProgessline() {
		return docApprovalProgessline;
	}

	public void setDocApprovalProgessline(String docApprovalProgessline) {
		this.docApprovalProgessline = docApprovalProgessline;
	}

	public String getDocFileName() {
		return docFileName;
	}

	public void setDocFileName(String docFileName) {
		this.docFileName = docFileName;
	}

	public Date getDocDraftDate() {
		return docDraftDate;
	}

	public void setDocDraftDate(Date docDraftDate) {
		this.docDraftDate = docDraftDate;
	}

	public int getDocNum() {
		return docNum;
	}

	public void setDocNum(int docNum) {
		this.docNum = docNum;
	}

	public String getDocTitle() {
		return docTitle;
	}

	public void setDocTitle(String docTitle) {
		this.docTitle = docTitle;
	}

	public String getDocApprovalline() {
		return docApprovalline;
	}

	public void setDocApprovalline(String docApprovalline) {
		this.docApprovalline = docApprovalline;
	}

	public int getDocTotalApprovalCount() {
		return docTotalApprovalCount;
	}

	public void setDocTotalApprovalCount(int docTotalApprovalCount) {
		this.docTotalApprovalCount = docTotalApprovalCount;
	}

	public int getDocCurrentApprovalCount() {
		return docCurrentApprovalCount;
	}

	public void setDocCurrentApprovalCount(int docCurrentApprovalCount) {
		this.docCurrentApprovalCount = docCurrentApprovalCount;
	}

	public String getDocNextApprovalEmpno() {
		return docNextApprovalEmpno;
	}

	public void setDocNextApprovalEmpno(String docNextApprovalEmpno) {
		this.docNextApprovalEmpno = docNextApprovalEmpno;
	}

	public String getDocEmpname() {
		return docEmpname;
	}

	public void setDocEmpname(String docEmpname) {
		this.docEmpname = docEmpname;
	}

	public String getDocEmpno() {
		return docEmpno;
	}

	public void setDocEmpno(String docEmpno) {
		this.docEmpno = docEmpno;
	}

	public String getDocContent() {
		return docContent;
	}

	public void setDocContent(String docContent) {
		this.docContent = docContent;
	}

	public String getDocFileurl() {
		return docFileurl;
	}

	public void setDocFileurl(String docFileurl) {
		this.docFileurl = docFileurl;
	}

	public String getDocState() {
		return docState;
	}

	public void setDocState(String docState) {
		this.docState = docState;
	}

	public String getDocType() {
		return docType;
	}

	public void setDocType(String docType) {
		this.docType = docType;
	}

	public String getDocDocunum() {
		return docDocunum;
	}

	public void setDocDocunum(String docDocunum) {
		this.docDocunum = docDocunum;
	}

}
