package RSR.Service;

import java.sql.Timestamp;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import RSR.DAO.RSRDBBean;
import RSR.DTO.RSRDataBean;

public class RSRInsertAction implements RSRRequestProInter{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("utf-8");
		
		RSRDataBean content = new RSRDataBean();
		
		content.setReserve_date(request.getParameter("reserve_date"));
		content.setReserve_time(request.getParameter("reserve_time"));
		content.setReserve_room(request.getParameter("reserve_room"));
		content.setReserve_name(request.getParameter("reserve_name"));
		content.setReserve_num(request.getParameter("reserve_num"));
		content.setRsr_date(new Timestamp(System.currentTimeMillis()));
		
		RSRDBBean rsPro = RSRDBBean.getInstance();
		
		rsPro.insertReserveContent(content);
		return "/RoomReserves/RSRListAction.rs";
	}
}