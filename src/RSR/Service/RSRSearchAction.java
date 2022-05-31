package RSR.Service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import RSR.DAO.RSRDBBean;
import RSR.DTO.RSRDataBean;

public class RSRSearchAction implements RSRRequestProInter{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("UTF-8");
		
		String search = request.getParameter("search");
		String searchn = request.getParameter("searchn");
		String pageNum = request.getParameter("pageNum");
		System.out.println(search);
		System.out.println(searchn);
		if(pageNum == null){
			pageNum = "1";
		}
		int pageSize = 10;
		int currentpage = Integer.parseInt(pageNum);
		int startRow = (currentpage -1) * pageSize + 1;
		int endRow = currentpage * pageSize;
		int count = 0;
		int number = 0;
		
		RSRDBBean rsPro = RSRDBBean.getInstance();
		count = rsPro.SearchCount(search,searchn);
		
		List<RSRDataBean> RSRList = rsPro.Search(search, pageNum, searchn);
		
		request.setAttribute("RSRList", RSRList);
		System.out.println(RSRList);
		for(int i=0; i<RSRList.size(); i++){
			System.out.println(RSRList.get(i).getReserve_date());
			System.out.println(RSRList.get(i).getReserve_room());
			System.out.println(RSRList.get(i).getReserve_time());
		}
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