package EDL.Service;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import EDL.DTO.EDLBean;
import EDL.DTO.EDLPageBean;
import EDL.DAO.EDLDBBean;

import java.util.ArrayList;
import java.util.List;

public class EDLListAction implements EDLRequestProInter {
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null) {
			pageNum = "1";
		}
		
		EDLDBBean DB = EDLDBBean.getInstance();
		EDLPageBean pageCount = DB.getListCnt();
		pageCount.setPageNum(Integer.parseInt(pageNum));
		List<EDLBean> PDFList = DB.getAllList(pageNum);
		pageCount.setTotalPageCnt((int) Math.ceil(pageCount.getTotalCnt() / 10.0));
		request.setAttribute("PDFList", PDFList);
		request.setAttribute("pageCount", pageCount);

		return "/EDL/main.jsp";
	}

}
