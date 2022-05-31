package Boa.Service;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Boa.DAO.BoaDBBean;
import Boa.DTO.BoaDataBean;

public class BoaUpdateAction implements BoaRequestProInter {

	@Override
	public String requestAc(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		String board_num =request.getParameter("board_num");
		
		
		BoaDBBean dl = BoaDBBean.getInstace();
		
		BoaDataBean br = dl.updatenum(board_num);
		
		request.setAttribute("br", br);
		
		return "updateform.jsp?pageNum=1";
	}
	}