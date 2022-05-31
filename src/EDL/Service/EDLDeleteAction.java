package EDL.Service;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import EDL.DAO.EDLDBBean;
import EDL.DTO.EDLBean;

public class EDLDeleteAction implements EDLRequestProInter {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		String doc_num = request.getParameter("doc_num");

		EDLDBBean DB = EDLDBBean.getInstance();
		EDLBean PDFInfor = DB.getDetail(doc_num);
		
		int result_cnt = DB.deletePDFInformation(doc_num);

		if (result_cnt != 0) {
			deleteFile(PDFInfor.getDoc_path());
		}

		return "/EDL/list.gw?pageNum=1";
	}

	public void deleteFile(String path) {
		try {
			File file = new File(path);
			file.delete();
		} catch (NullPointerException e) {
			return;
		}
	}

}
