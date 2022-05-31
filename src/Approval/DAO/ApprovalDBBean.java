package Approval.DAO;

import java.sql.*;
import java.util.*;

import Approval.DTO.*;

public class ApprovalDBBean {
	private static ApprovalDBBean instance = new ApprovalDBBean();

	public static ApprovalDBBean getInstance() {
		return instance;
	}

	private Connection getConnection() throws Exception {
		String jdbcDriver = "jdbc:apache:commons:dbcp:pool";
		return DriverManager.getConnection(jdbcDriver);
	}

	public List<ApprovalUserBean> getOrganizationList(String dept_code) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ApprovalUserBean> UserList = null;
		try {

			conn = ApprovalDBBean.getInstance().getConnection();
			String sql = "select * from emp where dept_code = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dept_code);
			rs = pstmt.executeQuery();
			UserList = new ArrayList();
			ApprovalUserBean User = null;
			while (rs.next()) {
				User = new ApprovalUserBean();
				User.setEmpdeptCode(rs.getString("dept_code"));
				User.setEmpAddress(rs.getString("emp_address"));
				User.setEmpContact(rs.getString("emp_contact"));
				User.setEmpHiredate(rs.getString("emp_hiredate"));
				User.setEmpName(rs.getString("emp_name"));
				User.setEmpNo(rs.getString("emp_no"));
				User.setEmpPosition(rs.getString("emp_position"));
				User.setEmpProfileimg(rs.getString("emp_profileimg"));
				UserList.add(User);
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
		return UserList;
	}

	public List<ApprovalDeptBean> getDeptList() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ApprovalDeptBean> DeptList = null;
		try {

			conn = ApprovalDBBean.getInstance().getConnection();
			String sql = "select * from dept";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			DeptList = new ArrayList();
			ApprovalDeptBean Dept = null;
			while (rs.next()) {
				Dept = new ApprovalDeptBean();
				Dept.setDeptCode(rs.getString("dept_code"));
				Dept.setDeptName(rs.getString("dept_name"));
				Dept.setDeptRepresent(rs.getString("dept_represent"));
				DeptList.add(Dept);
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
		return DeptList;
	}

	public ApprovalUserBean getUserDetail(String empNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ApprovalUserBean User = null;
		try {

			conn = ApprovalDBBean.getInstance().getConnection();
			String sql = "select * from emp where emp_no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, empNo);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				User = new ApprovalUserBean();
				User.setEmpdeptCode(rs.getString("dept_code"));
				User.setEmpAddress(rs.getString("emp_address"));
				User.setEmpContact(rs.getString("emp_contact"));
				User.setEmpHiredate(rs.getString("emp_hiredate"));
				User.setEmpName(rs.getString("emp_name"));
				User.setEmpNo(rs.getString("emp_no"));
				User.setEmpPosition(rs.getString("emp_position"));
				User.setEmpProfileimg(rs.getString("emp_profileimg"));
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
		return User;
	}

	public List<ApprovalPartnerBean> getPartnerList(String companyName) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ApprovalPartnerBean> PartnerList = null;
		try {

			conn = ApprovalDBBean.getInstance().getConnection();
			String sql = "select * from partner where partner_companyname like '%" + companyName + "%'";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			PartnerList = new ArrayList();
			ApprovalPartnerBean Partner = null;
			while (rs.next()) {
				Partner = new ApprovalPartnerBean();
				Partner.setPartnerCompanyName(rs.getString("partner_companyname"));
				Partner.setPartnerAddress(rs.getString("partner_address"));
				Partner.setPartnerRepresentative(rs.getString("partner_reprecent"));
				PartnerList.add(Partner);
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
		return PartnerList;
	}

	public int DraftDocument(ApprovalDocumentBean document) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result_cnt = 0;
		try {
			conn = ApprovalDBBean.getInstance().getConnection();
			String sql = "insert into approvallist(doc_num, doc_title, doc_approvalline,";
			sql += "doc_empname, doc_empno, doc_content, doc_state, doc_totallinecount,";
			sql += "doc_currentlinecount, doc_nextapprovalempno, doc_type, doc_docunum, doc_approvalprogressline, ";
			sql += "doc_draftdate, doc_references, doc_filename, doc_fileurl) values(seq_docu.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, sysdate, ?,?,?)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, document.getDocTitle());
			pstmt.setString(2, document.getDocApprovalline());
			pstmt.setString(3, document.getDocEmpname());
			pstmt.setString(4, document.getDocEmpno());
			pstmt.setString(5, document.getDocContent());
			pstmt.setString(6, document.getDocState());
			pstmt.setInt(7, document.getDocTotalApprovalCount());
			pstmt.setInt(8, document.getDocCurrentApprovalCount());
			pstmt.setString(9, document.getDocNextApprovalEmpno());
			pstmt.setString(10, document.getDocType());
			pstmt.setString(11, document.getDocDocunum());
			pstmt.setString(12, document.getDocApprovalProgessline());

			String references = "";
			for (String reference : document.getReferences()) {
				references += reference;
			}
			pstmt.setString(13, references);
			pstmt.setString(14, document.getDocFileName());
			pstmt.setString(15, document.getDocFileurl());
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

	public ApprovalDocumentBean getViewDocument(int docNum) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ApprovalDocumentBean document = null;
		try {

			conn = ApprovalDBBean.getInstance().getConnection();
			String sql = "select * from approvallist where doc_num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, docNum);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				document = new ApprovalDocumentBean();
				document.setDocNum(rs.getInt("doc_num"));
				document.setDocTitle(rs.getString("doc_title"));
				document.setDocApprovalline(rs.getString("doc_approvalline"));
				document.setDocApprovalProgessline(rs.getString("doc_approvalprogressline"));
				document.setDocEmpname(rs.getString("doc_empname"));
				document.setDocEmpno(rs.getString("doc_empno"));
				document.setDocContent(rs.getString("doc_content"));
				document.setDocFileurl(rs.getString("doc_fileurl"));
				document.setDocFileName(rs.getString("doc_filename"));
				document.setDocState(rs.getString("doc_state"));
				document.setDocTotalApprovalCount(rs.getInt("doc_totallinecount"));
				document.setDocCurrentApprovalCount(rs.getInt("doc_currentlinecount"));
				document.setDocNextApprovalEmpno(rs.getString("doc_nextapprovalempno"));
				document.setDocType(rs.getString("doc_type"));
				document.setDocDocunum(rs.getString("doc_docunum"));
				document.setDocDraftDate(rs.getDate("doc_draftdate"));
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
		return document;
	}

	public ApprovalDocuInforBean getDocumentProperty(String docuNum) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ApprovalDocuInforBean docuInfor = null;
		try {

			conn = ApprovalDBBean.getInstance().getConnection();
			String sql = "select * from approvaldocument where appdoc_num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, docuNum);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				docuInfor = new ApprovalDocuInforBean();
				docuInfor.setAppdocNum(rs.getInt("appdoc_num"));
				docuInfor.setAppdocName(rs.getString("appdoc_name"));
				docuInfor.setAppdocReadUrl(rs.getString("appdoc_readurl"));
				docuInfor.setAppdocUrl(rs.getString("appdoc_url"));
				docuInfor.setAppdocType("appdoc_type");

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
		return docuInfor;
	}

	public List<ApprovalDocumentBean> getUnfinishedList(String empNo, String pageNum) {// 미결함리스트
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ApprovalDocumentBean> documentList = null;
		try {
			
			int pageSize = 10;
			int currentPage = Integer.parseInt(pageNum);
			int startRow = (currentPage * 10) - 9;
			int endRow = currentPage * pageSize;
			int count = 0;
			int number = 0;
			
			conn = ApprovalDBBean.getInstance().getConnection();
			
			String sql = "select doc_num, doc_title, doc_approvalline, doc_approvalprogressline, doc_empname, doc_empno, doc_content, doc_fileurl, doc_filename, doc_state, doc_totallinecount, doc_currentlinecount, doc_nextapprovalempno, doc_type, doc_docunum, doc_draftdate, doc_references, r "
					+ "from (select doc_num, doc_title, doc_approvalline, doc_approvalprogressline, doc_empname, doc_empno, doc_content, doc_fileurl, doc_filename, doc_state, doc_totallinecount, doc_currentlinecount, doc_nextapprovalempno, doc_type, doc_docunum, doc_draftdate, doc_references, rownum r "
					+ "from (select doc_num, doc_title, doc_approvalline, doc_approvalprogressline, doc_empname, doc_empno, doc_content, doc_fileurl, doc_filename, doc_state, doc_totallinecount, doc_currentlinecount, doc_nextapprovalempno, doc_type, doc_docunum, doc_draftdate, doc_references "
					+ "from approvallist where (doc_state = 'Draft' or doc_state = 'Approval') and doc_nextapprovalempno = ? order by doc_num desc)) where r >= ? and r <= ?";
			
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, empNo);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rs = pstmt.executeQuery();
			documentList = new ArrayList();
			ApprovalDocumentBean document = null;
			while (rs.next()) {
				document = new ApprovalDocumentBean();
				document.setDocNum(rs.getInt("doc_num"));
				document.setDocTitle(rs.getString("doc_title"));
				document.setDocApprovalline(rs.getString("doc_approvalline"));
				document.setDocApprovalProgessline(rs.getString("doc_approvalprogressline"));
				document.setDocEmpname(rs.getString("doc_empname"));
				document.setDocEmpno(rs.getString("doc_empno"));
				document.setDocContent(rs.getString("doc_content"));
				document.setDocFileurl(rs.getString("doc_fileurl"));
				document.setDocFileName(rs.getString("doc_filename"));
				document.setDocState(rs.getString("doc_state"));
				document.setDocTotalApprovalCount(rs.getInt("doc_totallinecount"));
				document.setDocCurrentApprovalCount(rs.getInt("doc_currentlinecount"));
				document.setDocNextApprovalEmpno(rs.getString("doc_nextapprovalempno"));
				document.setDocType(rs.getString("doc_type"));
				document.setDocDocunum(rs.getString("doc_docunum"));
				document.setDocDraftDate(rs.getDate("doc_draftdate"));
				document.setDocInfor(getDocumentProperty(rs.getString("doc_docunum")));
				documentList.add(document);
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
		return documentList;
	}

	public List<ApprovalDocumentBean> getAllList(String empNo, String pageNum) {// 내 문서함
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ApprovalDocumentBean> documentList = null;
		try {
			
			
			int pageSize = 10;
			int currentPage = Integer.parseInt(pageNum);
			int startRow = (currentPage * 10) - 9;
			int endRow = currentPage * pageSize;
			int count = 0;
			int number = 0;
			
			
			conn = ApprovalDBBean.getInstance().getConnection();
			
			String sql = "select doc_num, doc_title, doc_approvalline, doc_approvalprogressline, doc_empname, doc_empno, doc_content, doc_fileurl, doc_filename, doc_state, doc_totallinecount, doc_currentlinecount, doc_nextapprovalempno, doc_type, doc_docunum, doc_draftdate, doc_references, r "
			+ "from (select doc_num, doc_title, doc_approvalline, doc_approvalprogressline, doc_empname, doc_empno, doc_content, doc_fileurl, doc_filename, doc_state, doc_totallinecount, doc_currentlinecount, doc_nextapprovalempno, doc_type, doc_docunum, doc_draftdate, doc_references, rownum r "
			+ "from (select doc_num, doc_title, doc_approvalline, doc_approvalprogressline, doc_empname, doc_empno, doc_content, doc_fileurl, doc_filename, doc_state, doc_totallinecount, doc_currentlinecount, doc_nextapprovalempno, doc_type, doc_docunum, doc_draftdate, doc_references "
			+ "from approvallist where doc_empno = ? order by doc_num desc)) where r >= ? and r <= ?";
			
			
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, empNo);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			
			rs = pstmt.executeQuery();
			documentList = new ArrayList();
			ApprovalDocumentBean document = null;
			while (rs.next()) {
				document = new ApprovalDocumentBean();
				document.setDocNum(rs.getInt("doc_num"));
				document.setDocTitle(rs.getString("doc_title"));
				document.setDocApprovalline(rs.getString("doc_approvalline"));
				document.setDocApprovalProgessline(rs.getString("doc_approvalprogressline"));
				document.setDocEmpname(rs.getString("doc_empname"));
				document.setDocEmpno(rs.getString("doc_empno"));
				document.setDocContent(rs.getString("doc_content"));
				document.setDocFileurl(rs.getString("doc_fileurl"));
				document.setDocFileName(rs.getString("doc_filename"));
				document.setDocState(rs.getString("doc_state"));
				document.setDocTotalApprovalCount(rs.getInt("doc_totallinecount"));
				document.setDocCurrentApprovalCount(rs.getInt("doc_currentlinecount"));
				document.setDocNextApprovalEmpno(rs.getString("doc_nextapprovalempno"));
				document.setDocType(rs.getString("doc_type"));
				document.setDocDocunum(rs.getString("doc_docunum"));
				document.setDocDraftDate(rs.getDate("doc_draftdate"));
				document.setDocInfor(getDocumentProperty(rs.getString("doc_docunum")));
				documentList.add(document);
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
		return documentList;
	}

	public int UpdateDocument(int docNum, ApprovalDocumentBean document) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int resultcnt = 0;
		try {

			conn = ApprovalDBBean.getInstance().getConnection();
			String sql = "update approvallist set doc_title=?,doc_approvalline=?,"
					+ "doc_approvalprogressline=?,doc_empname=?,doc_empno=?,doc_content=?,"
					+ "doc_fileurl=?,doc_filename=?,doc_state=?,doc_totallinecount=?,"
					+ "doc_currentlinecount=?,doc_nextapprovalempno=?,doc_type=?," + "doc_docunum=?,doc_draftdate=?"
					+ "where doc_num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, document.getDocTitle());
			pstmt.setString(2, document.getDocApprovalline());
			pstmt.setString(3, document.getDocApprovalProgessline());
			pstmt.setString(4, document.getDocEmpname());
			pstmt.setString(5, document.getDocEmpno());
			pstmt.setString(6, document.getDocContent());
			pstmt.setString(7, document.getDocFileurl());
			pstmt.setString(8, document.getDocFileName());
			pstmt.setString(9, document.getDocState());
			pstmt.setInt(10, document.getDocTotalApprovalCount());
			pstmt.setInt(11, document.getDocCurrentApprovalCount());
			pstmt.setString(12, document.getDocNextApprovalEmpno());
			pstmt.setString(13, document.getDocType());
			pstmt.setString(14, document.getDocDocunum());
			pstmt.setDate(15, (java.sql.Date) document.getDocDraftDate());
			pstmt.setInt(16, docNum);
			resultcnt = pstmt.executeUpdate();

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
		return resultcnt;
	}

	public List<ApprovalDocumentBean> getFinishedList(String empNo, String pageNum) {// 완료함
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ApprovalDocumentBean> documentList = null;
		try {
			
			int pageSize = 10;
			int currentPage = Integer.parseInt(pageNum);
			int startRow = (currentPage * 10) - 9;
			int endRow = currentPage * pageSize;
			int count = 0;
			int number = 0;
			
			conn = ApprovalDBBean.getInstance().getConnection();

			String sql = "select doc_num, doc_title, doc_approvalline, doc_approvalprogressline, doc_empname, doc_empno, doc_content, doc_fileurl, doc_filename, doc_state, doc_totallinecount, doc_currentlinecount, doc_nextapprovalempno, doc_type, doc_docunum, doc_draftdate, doc_references, r "
					+ "from (select doc_num, doc_title, doc_approvalline, doc_approvalprogressline, doc_empname, doc_empno, doc_content, doc_fileurl, doc_filename, doc_state, doc_totallinecount, doc_currentlinecount, doc_nextapprovalempno, doc_type, doc_docunum, doc_draftdate, doc_references, rownum r "
					+ "from (select doc_num, doc_title, doc_approvalline, doc_approvalprogressline, doc_empname, doc_empno, doc_content, doc_fileurl, doc_filename, doc_state, doc_totallinecount, doc_currentlinecount, doc_nextapprovalempno, doc_type, doc_docunum, doc_draftdate, doc_references "
					+ "from approvallist where doc_empno = ? and doc_state = 'finished' order by doc_num desc)) where r >= ? and r <= ?";
			
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, empNo);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rs = pstmt.executeQuery();
			documentList = new ArrayList();
			ApprovalDocumentBean document = null;
			while (rs.next()) {
				document = new ApprovalDocumentBean();
				document.setDocNum(rs.getInt("doc_num"));
				document.setDocTitle(rs.getString("doc_title"));
				document.setDocApprovalline(rs.getString("doc_approvalline"));
				document.setDocApprovalProgessline(rs.getString("doc_approvalprogressline"));
				document.setDocEmpname(rs.getString("doc_empname"));
				document.setDocEmpno(rs.getString("doc_empno"));
				document.setDocContent(rs.getString("doc_content"));
				document.setDocFileurl(rs.getString("doc_fileurl"));
				document.setDocFileName(rs.getString("doc_filename"));
				document.setDocState(rs.getString("doc_state"));
				document.setDocTotalApprovalCount(rs.getInt("doc_totallinecount"));
				document.setDocCurrentApprovalCount(rs.getInt("doc_currentlinecount"));
				document.setDocNextApprovalEmpno(rs.getString("doc_nextapprovalempno"));
				document.setDocType(rs.getString("doc_type"));
				document.setDocDocunum(rs.getString("doc_docunum"));
				document.setDocDraftDate(rs.getDate("doc_draftdate"));
				document.setDocInfor(getDocumentProperty(rs.getString("doc_docunum")));
				documentList.add(document);
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
		return documentList;
	}

	public List<ApprovalDocumentBean> getReferencedList(String empNo,String pageNum) {// 참조함
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ApprovalDocumentBean> documentList = null;
		try {
			int pageSize = 10;
			int currentPage = Integer.parseInt(pageNum);
			int startRow = (currentPage * 10) - 9;
			int endRow = currentPage * pageSize;
			int count = 0;
			int number = 0;
			conn = ApprovalDBBean.getInstance().getConnection();
			
			String sql = "select doc_num, doc_title, doc_approvalline, doc_approvalprogressline, doc_empname, doc_empno, doc_content, doc_fileurl, doc_filename, doc_state, doc_totallinecount, doc_currentlinecount, doc_nextapprovalempno, doc_type, doc_docunum, doc_draftdate, doc_references, r "
					+ "from (select doc_num, doc_title, doc_approvalline, doc_approvalprogressline, doc_empname, doc_empno, doc_content, doc_fileurl, doc_filename, doc_state, doc_totallinecount, doc_currentlinecount, doc_nextapprovalempno, doc_type, doc_docunum, doc_draftdate, doc_references, rownum r "
					+ "from (select doc_num, doc_title, doc_approvalline, doc_approvalprogressline, doc_empname, doc_empno, doc_content, doc_fileurl, doc_filename, doc_state, doc_totallinecount, doc_currentlinecount, doc_nextapprovalempno, doc_type, doc_docunum, doc_draftdate, doc_references "
					+ "from approvallist where doc_references like '%" + empNo + "%' order by doc_num desc)) where r >= ? and r <= ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			documentList = new ArrayList();
			ApprovalDocumentBean document = null;
			while (rs.next()) {
				document = new ApprovalDocumentBean();
				document.setDocNum(rs.getInt("doc_num"));
				document.setDocTitle(rs.getString("doc_title"));
				document.setDocApprovalline(rs.getString("doc_approvalline"));
				document.setDocApprovalProgessline(rs.getString("doc_approvalprogressline"));
				document.setDocEmpname(rs.getString("doc_empname"));
				document.setDocEmpno(rs.getString("doc_empno"));
				document.setDocContent(rs.getString("doc_content"));
				document.setDocFileurl(rs.getString("doc_fileurl"));
				document.setDocFileName(rs.getString("doc_filename"));
				document.setDocState(rs.getString("doc_state"));
				document.setDocTotalApprovalCount(rs.getInt("doc_totallinecount"));
				document.setDocCurrentApprovalCount(rs.getInt("doc_currentlinecount"));
				document.setDocNextApprovalEmpno(rs.getString("doc_nextapprovalempno"));
				document.setDocType(rs.getString("doc_type"));
				document.setDocDocunum(rs.getString("doc_docunum"));
				document.setDocDraftDate(rs.getDate("doc_draftdate"));
				document.setDocInfor(getDocumentProperty(rs.getString("doc_docunum")));
				/*if (rs.getString("doc_references") != null) {
					String temp_reference = rs.getString("doc_references");
					StringTokenizer st = new StringTokenizer(temp_reference, ";");
					List<String> references = new ArrayList();
					while (st.hasMoreTokens()) {
						references.add(st.nextToken());
					}
					document.setReferences(references);
				}*/
				documentList.add(document);
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
		return documentList;
	}
	
	public ApprovalPageBean getListCnt(String empNo, String listtype) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int totalCnt = 0;
		ApprovalPageBean pageCount = new ApprovalPageBean();

		try {

			conn = ApprovalDBBean.getInstance().getConnection();
			
			String sql = "";
			
			if(listtype.equals("mylist")){
				sql = "select count(*) from approvallist where doc_empno = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, empNo);
			}else if(listtype.equals("reference")){
				sql = "select count(*) from approvallist where doc_references like '%" + empNo + "%'";
				pstmt = conn.prepareStatement(sql);

			}else if(listtype.equals("finished")){
				sql = "select count(*) from approvallist where doc_empno = ? and doc_state = 'finished'";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, empNo);
			}else if(listtype.equals("unfinished")){
				sql = "select count(*) from approvallist where (doc_state = 'Draft' or doc_state = 'Approval') and doc_nextapprovalempno = ?  order by doc_num desc";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, empNo);
			}
			rs = pstmt.executeQuery();
			if (rs.next()) {
				pageCount.setTotalCnt(rs.getInt(1));
				System.out.println(rs.getInt(1));
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
}