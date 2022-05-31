package EDL.Service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import EDL.DAO.EDLDBBean;
import EDL.DTO.EDLBean;
import EDL.DTO.EDLPageBean;

public class EDLSearchAction implements EDLRequestProInter {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		String pageNum = request.getParameter("pageNum");
		String title = request.getParameter("title");
		if (pageNum == null) {
			pageNum = "1";
		}
		EDLDBBean DB = EDLDBBean.getInstance();
		EDLPageBean pageCount = DB.getSearchCnt(title);
		pageCount.setPageNum(Integer.parseInt(pageNum));
		List<EDLBean> PDFList = DB.getSearchList(pageNum, title);
		pageCount.setTotalPageCnt((int) Math.ceil(pageCount.getTotalCnt() / 10.0));
		request.setAttribute("PDFList", PDFList);
		request.setAttribute("pageCount", pageCount);
		System.out.println();

		return "/EDL/main.jsp?pageNum=1";
	}

}
