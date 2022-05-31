package PTM.Service;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.oreilly.servlet.MultipartRequest;


import PTM.DAO.BoardDBBean;
import PTM.DTO.BoardDataBean;

public class PTMInsertAction implements PTMRequestProInter {

	@Override
	public String requestAc(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		request.setCharacterEncoding("utf-8");

		String realFolder = request.getServletContext().getRealPath("/img");
		MultipartRequest multi = new MultipartRequest(request, realFolder, 5*1024*1024, "UTF-8");
		String partner_image1 = multi.getOriginalFileName("partner_image1");
		String partner_contact = multi.getParameter("partner_contact");
		String partner_address = multi.getParameter("partner_address");
		String partner_reprecent =multi.getParameter("partner_reprecent");
		String partner_companyname =multi.getParameter("partner_companyname");
		String partner_contractdate =multi.getParameter("partner_contractdate");
		String partner_tradeitem = multi.getParameter("partner_tradeitem");
		
		BoardDataBean data = new BoardDataBean();
		
	
	
		data.setPartner_contact(partner_contact);
		data.setPartner_address(partner_address);
		data.setPartner_reprecent(partner_reprecent);
		data.setPartner_companyname(partner_companyname);
		data.setPartner_contractdate(partner_contractdate);
		data.setPartner_tradeitem(partner_tradeitem);
        data.setPartner_image1(partner_image1);
		BoardDBBean db = BoardDBBean.getInstance();

		db.insertdata(data);
		return "/PTM/PTMList.ma?pageNum=1";
		
		

	}
	

}


