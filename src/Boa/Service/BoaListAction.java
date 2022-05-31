package Boa.Service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Boa.DAO.BoaDBBean;
import Boa.DTO.BoaDataBean;

public class BoaListAction implements BoaRequestProInter{
	
	@Override
	public String requestAc(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("utf-8");
		String pageNum = request.getParameter("pageNum");
	
		if (pageNum == null) {
			pageNum = "1";
		}
		int pageSize = 10;
		int currentpage = Integer.parseInt(pageNum);
		int startRow = (currentpage - 1) * pageSize + 1;
		int endRow = currentpage * pageSize;
		int count = 0;
		int number = 0;
		List dbList = null;
		
		BoaDBBean db = BoaDBBean.getInstace();
		count = db.getConuter();

		if (count > 0) {
			dbList = db.listboard(startRow, endRow);
		} else {
			System.out.println("no");
		}
		
		number=count-(currentpage-1)*pageSize;
	
		request.setAttribute("dblist", dbList);
		request.setAttribute("currentpage", new Integer(currentpage));
		request.setAttribute("startRow", new Integer(startRow));
		request.setAttribute("endRow", new Integer(endRow));
		request.setAttribute("count", new Integer(count));
		request.setAttribute("pageSize", new Integer(pageSize));
		request.setAttribute("number", new Integer(number));
		
		
		return "/board/main.jsp";
	}
}

		
