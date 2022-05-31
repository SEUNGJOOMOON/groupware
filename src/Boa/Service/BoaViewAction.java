package Boa.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Boa.DAO.BoaDBBean;
import Boa.DTO.BoaDataBean;

public class BoaViewAction implements BoaRequestProInter {

	@Override
	public String requestAc(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		String board_title = request.getParameter("board_title");

		BoaDBBean db = BoaDBBean.getInstace();

		BoaDataBean bro = db.getview(board_title);

		request.setAttribute("bro", bro);

		return "/board/informationform.jsp";
	}

}
