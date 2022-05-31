package HR.Service;

import javax.servlet.http.*;
import HR.DAO.*;
import HR.DTO.*;

public class HRImformationFAction implements HRInterface{
	public String requestPro(HttpServletRequest request, 
			HttpServletResponse response) throws Throwable{
		
		String emp_no = request.getParameter("emp_no");
		HRDBBean manager = HRDBBean.getInstance();
		HRDataBean a = manager.imformation(emp_no);
		
		request.setAttribute("a",a);
		return "/HR/imformationForm.jsp";
	
	}
}
