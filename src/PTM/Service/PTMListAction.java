package PTM.Service;

import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import PTM.DAO.BoardDBBean;
import PTM.DTO.BoardDataBean;

public class PTMListAction implements PTMRequestProInter {

	@Override
	public String requestAc(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
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
		List dbList = null;
		
		BoardDBBean dbpro = BoardDBBean.getInstance();
		count = dbpro.getConut();

		if (count > 0) {
			dbList = dbpro.listboard(startRow, endRow);
		} else {
			System.out.println("no");
		}
		/*for(BoardDataBean dbbean : dbList){
			
		}*/
		number=count-(currentpage-1)*pageSize;
	
		request.setAttribute("dblist", dbList);
		request.setAttribute("currentpage", new Integer(currentpage));
		request.setAttribute("startRow", new Integer(startRow));
		request.setAttribute("endRow", new Integer(endRow));
		request.setAttribute("count", new Integer(count));
		request.setAttribute("pageSize", new Integer(pageSize));
		request.setAttribute("number", new Integer(number));
		
		
		return "/PartnerManage/mainform.jsp";
	}
}
