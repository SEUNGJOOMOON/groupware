package HRmag.DTO;

import java.sql.*;

public class HRmagDataBean {
	private String emp_no;
	private String emp_name;
	private String dept_code;
	private String dept_name;
	private String emp_password;
	private String emp_position;
	private String emp_contact;
	private String emp_address;
	private String emp_profileimg;
	private String emp_hiredate;
	private String attend_time;
	private String getoff_time;
	private String late_reason;
	private String dept_represent;

	public String getDept_name() {
		return dept_name;
	}

	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}

	public String getEmp_password() {
		return emp_password;
	}

	public void setEmp_password(String emp_password) {
		this.emp_password = emp_password;
	}

	public String getDept_represent() {
		return dept_represent;
	}

	public void setDept_represent(String dept_represent) {
		this.dept_represent = dept_represent;
	}

	public String getEmp_address() {
		return emp_address;
	}

	public void setEmp_address(String emp_address) {
		this.emp_address = emp_address;
	}

	public String getEmp_no() {
		return emp_no;
	}

	public void setEmp_no(String emp_no) {
		this.emp_no = emp_no;
	}

	public String getEmp_name() {
		return emp_name;
	}

	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}

	public String getDept_code() {
		return dept_code;
	}

	public void setDept_code(String dept_code) {
		this.dept_code = dept_code;
	}

	public String getemp_password() {
		return emp_password;
	}

	public void setemp_password(String emp_password) {
		this.emp_password = emp_password;
	}

	public String getEmp_position() {
		return emp_position;
	}

	public void setEmp_position(String emp_position) {
		this.emp_position = emp_position;
	}

	public String getEmp_contact() {
		return emp_contact;
	}

	public void setEmp_contact(String emp_contact) {
		this.emp_contact = emp_contact;
	}

	public String getEmp_profileimg() {
		return emp_profileimg;
	}

	public void setEmp_profileimg(String emp_profileimg) {
		this.emp_profileimg = emp_profileimg;
	}

	public String getEmp_hiredate() {
		return emp_hiredate;
	}

	public void setEmp_hiredate(String emp_hiredate) {
		this.emp_hiredate = emp_hiredate;
	}

	public String getAttend_time() {
		return attend_time;
	}

	public void setAttend_time(String attend_time) {
		this.attend_time = attend_time;
	}

	public String getGetoff_time() {
		return getoff_time;
	}

	public void setGetoff_time(String getoff_time) {
		this.getoff_time = getoff_time;
	}

	public String getLate_reason() {
		return late_reason;
	}

	public void setLate_reason(String late_reason) {
		this.late_reason = late_reason;
	}

}
