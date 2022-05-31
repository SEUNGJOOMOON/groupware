package PTM.Service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import PTM.DAO.BoardDBBean;
import PTM.DTO.BoardDataBean;
import PTM.DTO.BoardSerchbean;

public class PTMSearchlistAction implements PTMRequestProInter {

	public String requestAc(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");

		

		String pageNum = request.getParameter("pageNum");
		
		String find =request.getParameter("partner_companyname");
		System.out.println(find);
		
		if (pageNum == null) {
			pageNum = "1";
		}
		int pageSize = 10;
		int currentpage = Integer.parseInt(pageNum);
		int startRow = (currentpage - 1) * pageSize + 1;
		int endRow = currentpage * pageSize;
		int count = 0;
		int number = 0;

		BoardDBBean bto = BoardDBBean.getInstance();
		count = bto.SearchCount(find);

		List<BoardDataBean> ptmlist = bto.Search(find, pageNum);
		
		System.out.println(find);
		request.setAttribute("dblist", ptmlist);
		request.setAttribute("count", count);
		request.setAttribute("currentpage", new Integer(currentpage));
		request.setAttribute("startRow", new Integer(startRow));
		request.setAttribute("endRow", new Integer(endRow));
		request.setAttribute("pageSize", new Integer(pageSize));
		request.setAttribute("number", new Integer(number));
		request.setAttribute("find", find);
		return "/PartnerManage/mainform.jsp?pageNum=1";

	}

}
