package HRmag.DTO;
import java.sql.*;
public class AttendanceDataBean {
private String emp_date;
private String emp_name;
private String emp_no;
private String dept_name;
private String attend_time;
private String getoff_time;
private String late_reason;
public String getEmp_name() {
	return emp_name;
}
public void setEmp_name(String emp_name) {
	this.emp_name = emp_name;
}
public String getEmp_no() {
	return emp_no;
}
public void setEmp_no(String emp_no) {
	this.emp_no = emp_no;
}
public String getDept_name() {
	return dept_name;
}
public void setDept_name(String dept_name) {
	this.dept_name = dept_name;
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
public String getEmp_date() {
	return emp_date;
}
public void setEmp_date(String emp_date) {
	this.emp_date = emp_date;
}
}
