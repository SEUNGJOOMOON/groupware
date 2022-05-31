package Approval.DTO;

public class ApprovalDocuInforBean {
	private int appdocNum;
	private String appdocName;
	private String appdocUrl;
	private String appdocReadUrl;
	public String getAppdocReadUrl() {
		return appdocReadUrl;
	}
	public void setAppdocReadUrl(String appdocReadUrl) {
		this.appdocReadUrl = appdocReadUrl;
	}
	private String appdocType;
	public int getAppdocNum() {
		return appdocNum;
	}
	public void setAppdocNum(int appdocNum) {
		this.appdocNum = appdocNum;
	}
	public String getAppdocName() {
		return appdocName;
	}
	public void setAppdocName(String appdocName) {
		this.appdocName = appdocName;
	}
	public String getAppdocUrl() {
		return appdocUrl;
	}
	public void setAppdocUrl(String appdocUrl) {
		this.appdocUrl = appdocUrl;
	}
	public String getAppdocType() {
		return appdocType;
	}
	public void setAppdocType(String appdocType) {
		this.appdocType = appdocType;
	}
}
