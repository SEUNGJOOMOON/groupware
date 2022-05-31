package HR.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import HR.DAO.*;
import HR.DTO.*;

public class HRAttendUpdateAction implements HRInterface{
	
	public String requestPro( HttpServletRequest request,
	        HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("utf-8");
		
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf2 = new SimpleDateFormat("hh:mm:ss");
		String emp_date = (String)sdf.format(d);
		String attend_time = (String)sdf2.format(d);
		String getoff_time = (String)sdf2.format(d);

		
		String emp_no = request.getParameter("emp_no");
		String late_reason = request.getParameter("late_reason");
		AttendBean a = new AttendBean();

		
		HRDBBean manager = HRDBBean.getInstance();
		
		if(request.getParameter("atttype").equals("Ãâ±Ù")){
			boolean result = manager.checkcommute(request.getParameter("emp_no"), emp_date);
			
			if(!result){
				a.setEmp_no(request.getParameter("emp_no"));
				a.setEmp_name(request.getParameter("emp_name"));
				a.setDept_name(request.getParameter("dept_name"));
				a.setEmp_date(emp_date);
				a.setAttend_time(attend_time);
				a.setGetoff_time("");
				a.setLate_reason(late_reason);
				
				manager.Insertattend(a);
				
			}else{
				a.setEmp_no(request.getParameter("emp_no"));
				a.setEmp_name(request.getParameter("emp_name"));
				a.setDept_name(request.getParameter("dept_name"));
				a.setEmp_date(emp_date);
				a.setAttend_time(attend_time);
				a.setGetoff_time("");
				a.setLate_reason(late_reason);
				
				manager.Updateattendatt(a);
			}
		}else if(request.getParameter("atttype").equals("Åð±Ù")){
			a.setEmp_no(request.getParameter("emp_no"));
			a.setEmp_name(request.getParameter("emp_name"));
			a.setDept_name(request.getParameter("dept_name"));
			a.setEmp_date(emp_date);
			a.setAttend_time(request.getParameter("attend_time"));
			a.setGetoff_time(getoff_time);
			a.setLate_reason(late_reason);
			
			manager.Updategetoff(a);
		}
		request.setAttribute("a", a);

		return "/HR/list.at";
	}

}
