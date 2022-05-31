package RSR.Service;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import RSR.DAO.RSRDBBean;

public class RSRListAction implements RSRRequestProInter {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		request.setCharacterEncoding("utf-8");
		String pageNum = request.getParameter("pageNum");
		if (request.getParameter("reserve_room") != null) {
			String search = request.getParameter("reserve_room");
		}

		if (pageNum == null) {
			pageNum = "1";
		}

		int pageSize = 10;
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = currentPage * pageSize;
		int count = 0;
		int number = 0;

		List RSRList = null;

		RSRDBBean rsPro = RSRDBBean.getInstance();
		count = rsPro.getContentCount();
		if (count > 0) {
			RSRList = rsPro.getContents(startRow, endRow, number, pageNum);
		} else {
			RSRList = Collections.EMPTY_LIST;
		}
		number = count - (currentPage - 1) * pageSize;

		request.setAttribute("currentPage", new Integer(currentPage));
		request.setAttribute("startRow", new Integer(startRow));
		request.setAttribute("endRow", new Integer(endRow));
		request.setAttribute("count", new Integer(count));
		request.setAttribute("pageSize", new Integer(pageSize));
		request.setAttribute("number", new Integer(number));
		request.setAttribute("RSRList", RSRList);

		return "/RoomReserves/reservelist.jsp";
	}
}
