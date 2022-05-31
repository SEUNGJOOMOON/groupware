package HRmag.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import HRmag.DAO.HRmagDBBean;
import HRmag.DTO.HRmagUserBean;

public class HRmagLoginProcessAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		String empno = request.getParameter("empno");
		String password = request.getParameter("password");

		HRmagDBBean db = HRmagDBBean.getInstance();
		HRmagUserBean user = db.LoginProcess(empno, password);
		if (user == null) {
			request.setAttribute("result", "failed");
		} else if (user != null) {
			request.setAttribute("result", "successed");
			HttpSession session = request.getSession();
			session.setAttribute("user_name", user.getEmpName());
			session.setAttribute("user_empno", user.getEmpNo());
			switch (user.getDeptCode()) {
			case "10":
				session.setAttribute("user_dept", "°³¹ßÆÀ");
				break;
			case "20":
				session.setAttribute("user_dept", "ÀÎ»çÆÀ");
				break;
			case "30":
				session.setAttribute("user_dept", "¿µ¾÷ÆÀ");
				break;
			case "40":
				session.setAttribute("user_dept", "°í°´Áö¿øÆÀ");
				break;
			}
			session.setAttribute("user_position", user.getEmpPosition());
			session.setAttribute("user_imgsrc", user.getEmpProfileImg());
			session.setAttribute("user_address", user.getEmpAddress());
			session.setAttribute("user_contact", user.getEmpContact());
			session.setAttribute("user_hiredate", user.getEmpHireDate());
		}
		return "/common/LoginProcess.jsp";
	}

}
