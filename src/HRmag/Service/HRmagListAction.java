package HRmag.Service;

import java.util.*;
import java.io.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.sun.org.apache.xpath.internal.operations.Mult;

import HRmag.Service.*;
import HRmag.DAO.*;
import HRmag.DTO.HRmagDataBean;

public class HRmagListAction implements CommandAction {
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");

		MultipartRequest multi = null;
		int max = 1024 * 1024 * 15;
		String savePath = request.getRealPath("profile");
		System.out.println(savePath);
		multi = new MultipartRequest(request, savePath, max, "utf-8", new DefaultFileRenamePolicy());

		String imagePath = multi.getFilesystemName("f_upload");

		if (imagePath == null) {
			savePath = "noimg";
		} else {
			savePath = savePath + "//" + imagePath;
		}
		String password = multi.getParameter("emp_password");
		String name = multi.getParameter("emp_name");
		String hiredate = multi.getParameter("emp_hiredate");
		String dept_code = multi.getParameter("dept_name");
		String position = multi.getParameter("emp_position");
		String contact = multi.getParameter("emp_contact");
		String address = multi.getParameter("emp_address");

		String[] str = hiredate.split("-");
		String empNo = str[0] + dept_code;

		HRmagDataBean hrList = new HRmagDataBean();
		hrList.setEmp_password(password);
		hrList.setEmp_no(empNo);
		hrList.setEmp_name(name);
		hrList.setEmp_hiredate(hiredate);
		hrList.setDept_code(dept_code);
		hrList.setEmp_position(position);
		hrList.setEmp_contact(contact);
		hrList.setEmp_address(address);
		hrList.setEmp_profileimg(savePath);

		HRmagDBBean hdb = HRmagDBBean.getInstance();
		hdb.insertList(hrList);

		return "/HRmag/getList.hm";
	}
}