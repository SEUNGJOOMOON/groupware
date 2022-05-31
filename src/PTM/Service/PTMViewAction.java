package PTM.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import PTM.DAO.BoardDBBean;
import PTM.DTO.BoardDataBean;

public class PTMViewAction implements PTMRequestProInter{
	@Override
	public String requestAc(HttpServletRequest request, HttpServletResponse response)throws Throwable{
		request.setCharacterEncoding("utf-8");
		
		String partner_companyname =request.getParameter("partner_companyname");
	
		
	
		BoardDBBean dpno = BoardDBBean.getInstance();
		
		BoardDataBean bro =dpno.getview(partner_companyname);

		request.setAttribute("bro",bro);
		
		return "/PartnerManage/informationform.jsp";
	}

}
