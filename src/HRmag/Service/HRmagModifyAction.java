package HRmag.Service;
import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import HRmag.Service.CommandAction;
import HRmag.DTO.*;
import HRmag.DAO.*;

public class HRmagModifyAction implements CommandAction {

    public String requestPro( HttpServletRequest request,
        HttpServletResponse response) throws Throwable {
     
        request.setCharacterEncoding("utf-8");
        String pageNum = request.getParameter("pageNum");
        if(pageNum == null) {
        	pageNum="1";
        }
        
        
        HRmagDataBean hrList = new HRmagDataBean();
        hrList.setEmp_no(request.getParameter("emp_no"));
        hrList.setEmp_name(request.getParameter("emp_name"));
		hrList.setDept_code(request.getParameter("dept_name"));
		hrList.setEmp_position(request.getParameter("emp_position"));
		hrList.setEmp_contact(request.getParameter("emp_contact"));
		hrList.setEmp_address(request.getParameter("emp_address"));
		hrList.setEmp_password(request.getParameter("emp_password"));
	
        HRmagDBBean hdb = HRmagDBBean.getInstance();
        hdb.updatehrList(hrList);

        request.setAttribute("hrList", hrList);
        
   
        return "/HRmag/getList.hm";
    }
}