package HR.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import HR.DAO.*;
import HR.DTO.*;

public class HRImformationCAction implements HRInterface{
	
	public String requestPro( HttpServletRequest request,
	        HttpServletResponse response) throws Throwable {

	        request.setCharacterEncoding("utf-8");
	        
	        String emp_no = request.getParameter("emp_no");
	        String emp_contact = request.getParameter("emp_contact");
	        String emp_address = request.getParameter("emp_address");
	        String emp_password = request.getParameter("emp_password");
	        
	HRDataBean a = new HRDataBean();
	a.setEmp_no(request.getParameter("emp_no"));
	a.setEmp_name(request.getParameter("emp_name"));
	a.setDept_code(request.getParameter("dept_code"));
	a.setEmp_position(request.getParameter("emp_position"));
	a.setEmp_address(request.getParameter("emp_address"));
	a.setEmp_contact(request.getParameter("emp_contact"));
	a.setEmp_hiredate(request.getParameter("emp_hiredate"));
	a.setEmp_password(request.getParameter("emp_password"));

	HRDBBean manager = HRDBBean.getInstance();
	manager.Updatemember(a);
	request.setAttribute("a",a);

	return "/HR/imformationModifyC.jsp";
	}	
}