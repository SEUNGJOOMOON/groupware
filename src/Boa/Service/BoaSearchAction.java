package Boa.Service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Boa.DAO.BoaDBBean;
import Boa.DTO.BoaDataBean;

public class BoaSearchAction implements BoaRequestProInter {

	@Override
	public String requestAc(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");

		String search = request.getParameter("Search");
		String sel = request.getParameter("sel");

		String pageNum = request.getParameter("pageNum");
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

		BoaDBBean bto = BoaDBBean.getInstace();
		count = bto.SearchCount(search,sel);

		List<BoaDataBean> ptmlist = bto.Search(search,pageNum,sel);
		
		System.out.println(search);
		request.setAttribute("dblist", ptmlist);
		request.setAttribute("count", count);
		request.setAttribute("currentpage", new Integer(currentpage));
		request.setAttribute("startRow", new Integer(startRow));
		request.setAttribute("endRow", new Integer(endRow));
		request.setAttribute("pageSize", new Integer(pageSize));
		request.setAttribute("number", new Integer(number));
		request.setAttribute("Search", search);
		request.setAttribute("sel", sel);
		return "/board/main.jsp?pageNum=1";
		
	}

}
