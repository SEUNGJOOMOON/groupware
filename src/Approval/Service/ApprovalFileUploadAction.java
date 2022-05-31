package Approval.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import EDL.DAO.EDLDBBean;
import EDL.DTO.EDLBean;

public class ApprovalFileUploadAction implements ApprovalRequestPRoInter {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		request.setCharacterEncoding("utf-8");

		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		String fileurl = "";
		String filename = "";
		if (!isMultipart) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
		DiskFileItemFactory factory = new DiskFileItemFactory();

		ServletFileUpload upload = new ServletFileUpload(factory);

		try {
			List<FileItem> items = upload.parseRequest(request);
			System.out.println(items.size());
			for (FileItem item : items) {
				String path = request.getRealPath("/");
				fileurl = Upload(path + "approvalfiles", item.getInputStream(), item.getName());
				filename = item.getName();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/Approval/common/FileUpload.jsp?fileurl=" + fileurl + "&filename=" + filename;
	}

	public static String Upload(String directory, InputStream is, String RealFileName) throws IOException {
		Random random = new Random();
		int randomValue = random.nextInt(10000);
		String fileName = "[Ã·ºÎÆÄÀÏ]" + randomValue + RealFileName;

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
			return "/Groupware/approvalfiles/" + fileName;
		}
	}

}
