package EDL.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import EDL.DAO.EDLDBBean;
import EDL.DTO.EDLBean;

public class EDLViewAction implements EDLRequestProInter {
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		EDLDBBean DB = EDLDBBean.getInstance();
		EDLBean PDFInfor = DB.getDetail(request.getParameter("doc_num"));
		request.setAttribute("PDFInfor", PDFInfor);
		return "/EDL/read.jsp";
	}

}
