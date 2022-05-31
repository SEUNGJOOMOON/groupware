package Approval.DTO;

public class ApprovalDeptBean {
	private String deptName;
	private String deptCode;
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getDeptCode() {
		return deptCode;
	}
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	public String getDeptRepresent() {
		return deptRepresent;
	}
	public void setDeptRepresent(String deptRepresent) {
		this.deptRepresent = deptRepresent;
	}
	private String deptRepresent;
}
