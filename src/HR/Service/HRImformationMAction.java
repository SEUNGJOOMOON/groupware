package HR.Service;
import java.util.*;
import javax.servlet.http.*;
import HR.DAO.*;
import HR.DTO.*;

public class HRImformationMAction implements HRInterface{
	public String requestPro( HttpServletRequest request,
	        HttpServletResponse response) throws Throwable {

	        request.setCharacterEncoding("utf-8");
	        
	String emp_no = request.getParameter("emp_no");
	
			HRDBBean manager = HRDBBean.getInstance();
			HRDataBean a = manager.imformation(emp_no);
			
			request.setAttribute("a",a);

	return "/HR/imformationModify.jsp";
	}
}