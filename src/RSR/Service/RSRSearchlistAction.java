package RSR.Service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import RSR.DAO.RSRDBBean;
import RSR.DTO.RSRDataBean;

public class RSRSearchlistAction implements RSRRequestProInter{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");

		String pageNum = request.getParameter("pageNum");
		String search = request.getParameter("search");
		String searchn = request.getParameter("searchn");
		System.out.println(search);
	
		if (pageNum == null) {
			pageNum = "1";
		}

		int pageSize = 10;
		int currentpage = Integer.parseInt(pageNum);
		int startRow = (currentpage - 1) * pageSize + 1;
		int endRow = currentpage * pageSize;
		int count = 0;
		int number = 0;
		
		RSRDBBean rspro = RSRDBBean.getInstance();
		count = rspro.SearchCount(search,searchn);

		List<RSRDataBean> RSRList = rspro.Search(search, pageNum, searchn);
		
		System.out.println(search);
		request.setAttribute("RSRList", RSRList);
		request.setAttribute("count", count);
		request.setAttribute("currentpage", new Integer(currentpage));
		request.setAttribute("startRow", new Integer(startRow));
		request.setAttribute("endRow", new Integer(endRow));
		request.setAttribute("pageSize", new Integer(pageSize));
		request.setAttribute("number", new Integer(number));
		request.setAttribute("search", search);
		request.setAttribute("searchn", searchn);
		return "/RoomReserves/reservelist.jsp?pageNum=1";

	}
}