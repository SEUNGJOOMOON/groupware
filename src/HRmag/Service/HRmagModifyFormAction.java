package HRmag.Service;


import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import HRmag.Service.CommandAction;
import HRmag.DAO.*;
import HRmag.DTO.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class HRmagModifyFormAction implements CommandAction {

    public String requestPro(HttpServletRequest request,
        HttpServletResponse response) throws Throwable {
    	
        String empNo =request.getParameter("emp_no");
    	String pageNum = request.getParameter("pageNum");
    	if(pageNum == null) {
    		pageNum="1";
    	}


        HRmagDBBean hdb = HRmagDBBean.getInstance();
        HRmagDataBean hrList =  hdb.updateGetList(empNo);
       
	
        request.setAttribute("hrList", hrList);
        request.setAttribute("pageNum", pageNum);
     
   

        return "/HR_mag/modifyForm.jsp";
    }
}

