package HR.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import HR.DAO.HRDBBean;

public class HRAttendCheckAction implements HRInterface {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		HRDBBean manager = HRDBBean.getInstance();
		
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf2 = new SimpleDateFormat("hh:mm:ss");
		String emp_date = (String)sdf.format(d);//오늘 날짜
		
		String emp_no = request.getParameter("emp_no");
		String result1 = "";
		boolean result = manager.checkcommute(emp_no, emp_date);
		System.out.println(result);
		if(result){
			result1 ="attend";//이미등록된것
		}else{
			result1 ="noattend";
		}
		return "/HR/attendcheck.jsp?result=" + result1;
	}

}
