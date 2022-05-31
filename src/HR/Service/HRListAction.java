package HR.Service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import HR.DAO.HRDBBean;
import HR.DTO.AttendBean;

public class HRListAction implements HRInterface {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		
		HRDBBean manager = HRDBBean.getInstance();
		List<AttendBean> attList = manager.Attendcheck(session.getAttribute("user_empno").toString());
		
		request.setAttribute("attList", attList);
		return "/HR/attendanceForm.jsp";
	}

}
