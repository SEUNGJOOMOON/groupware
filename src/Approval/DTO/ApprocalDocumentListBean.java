package Approval.DTO;
import java.util.List;

import Approval.DTO.ApprovalDocumentBean;

public class ApprocalDocumentListBean {

	private List<ApprovalDocumentBean> documentList;
	private String listType;
	public List<ApprovalDocumentBean> getDocumentList() {
		return documentList;
	}
	public void setDocumentList(List<ApprovalDocumentBean> documentList) {
		this.documentList = documentList;
	}
	public String getListType() {
		return listType;
	}
	public void setListType(String listType) {
		this.listType = listType;
	}
	
}
