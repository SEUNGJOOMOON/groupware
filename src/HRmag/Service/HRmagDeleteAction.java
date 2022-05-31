package HRmag.Service;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import HRmag.DAO.HRmagDBBean;
import HRmag.Service.CommandAction;
public class HRmagDeleteAction implements CommandAction {
public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
	
	
	String emp_no = request.getParameter("emp_no");
    String pageNum = request.getParameter("pageNum");
    if(pageNum == null) {
    	pageNum="1";
    }
	HRmagDBBean hdb = HRmagDBBean.getInstance();
	hdb.listDelete(emp_no);
	
	
	
	return "/HRmag/getList.hm?pageNum=1";
}
}