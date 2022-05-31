package EDL.DAO;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

import EDL.DTO.EDLBean;
import EDL.DTO.EDLPageBean;

public class EDLDBBean {
	private static EDLDBBean instance = new EDLDBBean();

	public static EDLDBBean getInstance() {
		return instance;
	}

	private Connection getConnection() throws Exception {
		String jdbcDriver = "jdbc:apache:commons:dbcp:pool";
		return DriverManager.getConnection(jdbcDriver);
	}

	public EDLPageBean getListCnt() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int totalCnt = 0;
		EDLPageBean pageCount = new EDLPageBean();

		try {

			conn = EDLDBBean.getInstance().getConnection();
			String sql = "select count(*) from electronic_document";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				pageCount.setTotalCnt(rs.getInt(1));
			}
		} catch (Exception e) {
			System.out.println("데이터베이스 에러 발생 : " + e.getMessage());
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (Exception e) {
				System.out.println("데이터베이스 에러 발생 : " + e.getMessage());
			}
		}
		return pageCount;
	}

	public int insertPDFInformation(EDLBean PDFInfo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result_cnt = 0;
		try {
			conn = EDLDBBean.getInstance().getConnection();
			String sql = "insert into electronic_document values(seq_EDL.nextval,?,?,?,?,?,?,sysdate)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, PDFInfo.getDoc_writer());
			pstmt.setString(2, PDFInfo.getDoc_dept());
			pstmt.setString(3, PDFInfo.getDoc_title());
			pstmt.setString(4, PDFInfo.getDoc_path());
			pstmt.setString(5, PDFInfo.getDoc_viewpath());
			pstmt.setString(6, PDFInfo.getDoc_realname());
			result_cnt = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("데이터베이스 에러 발생 : " + e.getMessage());
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (Exception e) {
				System.out.println("데이터베이스 에러 발생 : " + e.getMessage());
			}
		}

		return result_cnt;
	}

	public List<EDLBean> getAllList(String pageNum) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<EDLBean> PDFList = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
		try {

			int pageSize = 10;
			int currentPage = Integer.parseInt(pageNum);
			int startRow = (currentPage * 10) - 9;
			int endRow = currentPage * pageSize;
			int count = 0;
			int number = 0;

			conn = EDLDBBean.getInstance().getConnection();
			String sql = "select doc_num,doc_writer,doc_dept,doc_title,doc_path,doc_date,doc_realname,r,doc_viewpath "
					+ "from (select doc_num,doc_writer,doc_dept,doc_title,doc_path,doc_date,doc_realname,rownum r,doc_viewpath "
					+ "from (select doc_num,doc_writer,doc_dept,doc_title,doc_path,doc_date,doc_realname,doc_viewpath "
					+ "from electronic_document order by doc_num desc)) where r >= ? and r <= ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			PDFList = new ArrayList();
			EDLBean PDFInfor = null;
			while (rs.next()) {
				PDFInfor = new EDLBean();
				PDFInfor.setDoc_num(rs.getInt("doc_num"));
				PDFInfor.setDoc_writer(rs.getString("doc_writer"));
				PDFInfor.setDoc_dept(rs.getString("doc_dept"));
				PDFInfor.setDoc_title(rs.getString("doc_title"));
				PDFInfor.setDoc_path(rs.getString("doc_path"));
				PDFInfor.setDoc_viewpath(rs.getString("doc_viewpath"));
				PDFInfor.setDoc_realname(rs.getString("doc_realname"));
				PDFInfor.setRowNum(rs.getString("r"));
				PDFInfor.setDoc_date(makeCorrectMonth(rs.getDate("doc_date")));
				PDFList.add(PDFInfor);
			}
		} catch (Exception e) {
			System.out.println("데이터베이스 에러 발생 : " + e.getMessage());
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (Exception e) {
				System.out.println("데이터베이스 에러 발생 : " + e.getMessage());
			}
		}
		return PDFList;
	}

	public EDLBean getDetail(String doc_num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		EDLBean PDFInfor = null;
		try {
			conn = EDLDBBean.getInstance().getConnection();
			String sql = "select * from electronic_document where doc_num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, doc_num);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				PDFInfor = new EDLBean();
				PDFInfor.setDoc_num(rs.getInt("doc_num"));
				PDFInfor.setDoc_writer(rs.getString("doc_writer"));
				PDFInfor.setDoc_dept(rs.getString("doc_dept"));
				PDFInfor.setDoc_title(rs.getString("doc_title"));
				PDFInfor.setDoc_path(rs.getString("doc_path"));
				PDFInfor.setDoc_viewpath(rs.getString("doc_viewpath"));
				PDFInfor.setDoc_realname(rs.getString("doc_realname"));
				PDFInfor.setDoc_date(makeCorrectMonth(rs.getDate("doc_date")));
			}
		} catch (Exception e) {
			System.out.println("데이터베이스 에러 발생 : " + e.getMessage());
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (Exception e) {
				System.out.println("데이터베이스 에러 발생 : " + e.getMessage());
			}
		}
		return PDFInfor;
	}

	public String makeCorrectMonth(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MONTH, 0);
		String returnDate = sdf.format(c.getTime());
		return returnDate;
	}

	public int deletePDFInformation(String doc_num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result_cnt = 0;
		try {
			conn = EDLDBBean.getInstance().getConnection();
			String sql = "delete from electronic_document where doc_num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, doc_num);
			result_cnt = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("데이터베이스 에러 발생 : " + e.getMessage());
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (Exception e) {
				System.out.println("데이터베이스 에러 발생 : " + e.getMessage());
			}
		}
		return result_cnt;
	}

	public List<EDLBean> getSearchList(String pageNum, String title) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<EDLBean> PDFList = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
		try {

			int pageSize = 10;
			int currentPage = Integer.parseInt(pageNum);
			int startRow = (currentPage * 10) - 9;
			int endRow = currentPage * pageSize;
			int count = 0;
			int number = 0;

			conn = EDLDBBean.getInstance().getConnection();
			String sql = "select doc_num,doc_writer,doc_dept,doc_title,doc_path,doc_date,doc_realname,r,doc_viewpath "
					+ "from (select doc_num,doc_writer,doc_dept,doc_title,doc_path,doc_date,doc_realname,rownum r,doc_viewpath "
					+ "from (select doc_num,doc_writer,doc_dept,doc_title,doc_path,doc_date,doc_realname,doc_viewpath "
					+ "from electronic_document  where doc_title like '%" + title + "%' order by doc_num desc)) where r >= ? and r <= ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			PDFList = new ArrayList();
			EDLBean PDFInfor = null;
			while (rs.next()) {
				PDFInfor = new EDLBean();
				PDFInfor.setDoc_num(rs.getInt("doc_num"));
				PDFInfor.setDoc_writer(rs.getString("doc_writer"));
				PDFInfor.setDoc_dept(rs.getString("doc_dept"));
				PDFInfor.setDoc_title(rs.getString("doc_title"));
				PDFInfor.setDoc_path(rs.getString("doc_path"));
				PDFInfor.setDoc_viewpath(rs.getString("doc_viewpath"));
				PDFInfor.setDoc_realname(rs.getString("doc_realname"));
				PDFInfor.setRowNum(rs.getString("r"));
				PDFInfor.setDoc_date(makeCorrectMonth(rs.getDate("doc_date")));
				PDFList.add(PDFInfor);
			}
		} catch (Exception e) {
			System.out.println("데이터베이스 에러 발생1 : " + e.getMessage());
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (Exception e) {
				System.out.println("데이터베이스 에러 발생 : " + e.getMessage());
			}
		}
		return PDFList;
	}

	public EDLPageBean getSearchCnt(String title) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int totalCnt = 0;
		EDLPageBean pageCount = new EDLPageBean();

		try {

			conn = EDLDBBean.getInstance().getConnection();
			String sql = "select count(*) from electronic_document where doc_title LIKE '%" + title + "%'";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				pageCount.setTotalCnt(rs.getInt(1));
				pageCount.setSearchKeyword(title);
			}
		} catch (Exception e) {
			System.out.println("데이터베이스 에러 발생2 : " + e.getMessage());
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (Exception e) {
				System.out.println("데이터베이스 에러 발생 : " + e.getMessage());
			}
		}
		return pageCount;
	}

}
