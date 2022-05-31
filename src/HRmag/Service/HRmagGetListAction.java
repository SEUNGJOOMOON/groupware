package HRmag.Service;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import HRmag.Service.*;
import HRmag.DAO.*;
import HRmag.DTO.HRmagDataBean;
public class HRmagGetListAction implements CommandAction {
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable{
		
		String pageNum = request.getParameter("pageNum");
        
	 if(pageNum == null) {
		 pageNum = "1";
	 }
	 
	 int pageSize = 10;
	 int pageGroupSize =5;
	 int currentPage = Integer.parseInt(pageNum);
	 int startRow = (currentPage-1)*pageSize+1;
	 int endRow = currentPage*pageSize;
	 int count = 0;
	 int number = 0;
	List hrList = new ArrayList();
	HRmagDBBean hdb = HRmagDBBean.getInstance();
	count = hdb.getListCount();
	
	if(count>0) {
		if(endRow >count)
			endRow=count;
		hrList = hdb.getList(startRow, endRow);	

		
	}else{
		hrList = Collections.EMPTY_LIST;
		
	}
	number = count -(currentPage-1)*pageSize;
	int pageGroupCount = count/(pageSize*pageGroupSize)+(count%(pageSize*pageGroupSize)==0?0:1);
	int numPageGroup = (int)Math.ceil((double)currentPage/pageGroupSize);
	
	
	request.setAttribute("currentPage",new Integer(currentPage));
	request.setAttribute("startRow", new Integer(startRow));
	request.setAttribute("endRow", new Integer(endRow));
	request.setAttribute("count", new Integer(count));
	request.setAttribute("pageSize", new Integer(pageSize));
	request.setAttribute("number", new Integer(number));
	request.setAttribute("pageGroupSize", new Integer(pageGroupSize));
	request.setAttribute("numPageGroup",new Integer(numPageGroup));
	request.setAttribute("pageGroupCount", new Integer(pageGroupCount));
	request.setAttribute("hrList", hrList);
	
	

	return "/HR_mag/HRListForm.jsp";
	}
}