package Boa.Service;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Boa.DAO.BoaDBBean;
import Boa.DTO.BoaDataBean;
public class BoaUpdateproaction implements BoaRequestProInter {

	@Override
	public String requestAc(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		BoaDataBean bo = new BoaDataBean();
		
		
		
		String board_writer =request.getParameter("board_writer");
		
		System.out.println(board_writer);
		
		bo.setBoard_num(request.getParameter("board_num"));
		bo.setBoard_writerid(request.getParameter("board_writerid"));
		bo.setBoard_writer(request.getParameter("board_writer"));
		bo.setBoard_title(request.getParameter("board_title"));
		bo.setBoard_content(request.getParameter("board_content"));
  
		BoaDBBean db = BoaDBBean.getInstace();
		
		 db.updatepro(bo);
		
	

		
		return "/board/updateproform.jsp";
}
}