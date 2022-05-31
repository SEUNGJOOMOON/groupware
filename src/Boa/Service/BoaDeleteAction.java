package Boa.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Boa.DAO.BoaDBBean;
import Boa.DTO.BoaDataBean;

public class BoaDeleteAction implements BoaRequestProInter {

	@Override
	public String requestAc(HttpServletRequest request, HttpServletResponse response) throws Throwable {
    
		request.setCharacterEncoding("utf-8");
		int board_num = Integer.parseInt(request.getParameter("board_num"));
		System.out.println(board_num);
		
		
		
		BoaDBBean DB = BoaDBBean.getInstace();
	  DB.Delete(board_num);
		
		
		

		return "/board/deleteform.jsp";
	}

}
