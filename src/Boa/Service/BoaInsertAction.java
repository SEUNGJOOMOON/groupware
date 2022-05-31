package Boa.Service;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Boa.DAO.BoaDBBean;
import Boa.DTO.BoaDataBean;

public class BoaInsertAction implements BoaRequestProInter{

	@Override
	public String requestAc(HttpServletRequest request, HttpServletResponse response) throws Throwable {
	
		
		request.setCharacterEncoding("utf-8");
		BoaDataBean boa = new BoaDataBean();
		
		boa.setBoard_num(request.getParameter("board_num"));
		boa.setBoard_writerid(request.getParameter("board_writerid"));
		boa.setBoard_writer(request.getParameter("board_writer"));
		boa.setBoard_title(request.getParameter("board_title"));
		boa.setBoard_content(request.getParameter("board_content"));
		boa.setBoard_date(new Timestamp(System.currentTimeMillis()));

		BoaDBBean db = BoaDBBean.getInstace();
		
		db.insertdatad(boa);
		return "/board/boardList.bo?pageNum=1";
	}
	


}
