package EDL.Service;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import EDL.DTO.EDLBean;
import EDL.DAO.EDLDBBean;

import java.util.List;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Random;

public class EDLInsertAction implements EDLRequestProInter {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");

		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if (!isMultipart) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
		DiskFileItemFactory factory = new DiskFileItemFactory();

		ServletFileUpload upload = new ServletFileUpload(factory);

		EDLBean PDFInfo = new EDLBean();

		try {
			List<FileItem> items = upload.parseRequest(request);

			for (FileItem item : items) {
				if (item.isFormField()) { // 일반 데이터
					switch (item.getFieldName()) {
					case "txt_name":
						PDFInfo.setDoc_writer(item.getString("UTF-8"));
						break;
					case "txt_dept":
						PDFInfo.setDoc_dept(item.getString("UTF-8"));
						break;
					case "txt_title":
						PDFInfo.setDoc_title(item.getString("UTF-8"));
						break;
					}
				} else { // 파일 데이터
					PDFInfo.setDoc_realname(item.getName());
					String path = request.getRealPath("/");
					Upload(path + "pdfs", item.getInputStream(),
							PDFInfo);
				}

				if (PDFInfo.getDoc_path() != null) {
					EDLDBBean DB = EDLDBBean.getInstance();
					int result_cnt = DB.insertPDFInformation(PDFInfo);
					request.setAttribute("result_cnt", result_cnt);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/EDL/list.gw?pageNum=1";
	}

	public static EDLBean Upload(String directory, InputStream is, EDLBean PDFInfo) throws IOException {
		Random random = new Random();
		long currentTime = System.currentTimeMillis();
		int randomValue = random.nextInt(50);
		String fileName = Long.toString(currentTime) + "_" + Integer.toString(randomValue);

		File file = new File(directory, fileName);
		FileOutputStream os = null;
		try {
			os = new FileOutputStream(file);
			byte[] data = new byte[8096];
			int len = -1;
			while ((len = is.read(data)) != -1) {
				os.write(data, 0, len);
			}
		} finally {
			if (os != null)
				try {
					os.close();
				} catch (IOException e) {
				}
			PDFInfo.setDoc_path(file.getAbsolutePath());
			PDFInfo.setDoc_viewpath("/Groupware/pdfs/" + fileName);
		}
		return PDFInfo;
	}

}
