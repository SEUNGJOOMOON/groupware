package EDL.DTO;

public class EDLBean {
	private int doc_num;
	private String doc_writer;
	private String doc_dept;
	private String doc_title;
	private String doc_path;
	private String doc_viewpath;
	private String doc_date;
	private String doc_realname;
	private String rowNum;
	public String getRowNum() {
		return rowNum;
	}
	public void setRowNum(String rowNum) {
		this.rowNum = rowNum;
	}
	public String getDoc_realname() {
		return doc_realname;
	}
	public String getDoc_viewpath() {
		return doc_viewpath;
	}
	public void setDoc_viewpath(String doc_viewpath) {
		this.doc_viewpath = doc_viewpath;
	}
	public void setDoc_realname(String doc_realname) {
		this.doc_realname = doc_realname;
	}
	public int getDoc_num() {
		return doc_num;
	}
	public void setDoc_num(int doc_num) {
		this.doc_num = doc_num;
	}
	public String getDoc_writer() {
		return doc_writer;
	}
	public void setDoc_writer(String doc_writer) {
		this.doc_writer = doc_writer;
	}
	public String getDoc_dept() {
		return doc_dept;
	}
	public void setDoc_dept(String doc_dept) {
		this.doc_dept = doc_dept;
	}
	public String getDoc_title() {
		return doc_title;
	}
	public void setDoc_title(String doc_title) {
		this.doc_title = doc_title;
	}
	public String getDoc_path() {
		return doc_path;
	}
	public void setDoc_path(String doc_path) {
		this.doc_path = doc_path;
	}
	public String getDoc_date() {
		return doc_date;
	}
	public void setDoc_date(String doc_date) {
		this.doc_date = doc_date;
	}
	
	
}
