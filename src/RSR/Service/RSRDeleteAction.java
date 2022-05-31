package RSR.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import RSR.DAO.RSRDBBean;
public class RSRDeleteAction implements RSRRequestProInter{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("utf-8");
		String reserve_listnum = request.getParameter("reserve_listnum");
		String reserve_num = request.getParameter("checkcode");
		RSRDBBean rsPro = RSRDBBean.getInstance();
		rsPro.deleteRSRConetent(reserve_listnum, reserve_num);		
		return "/RoomReserves/RSRListAction.rs";
	}
}