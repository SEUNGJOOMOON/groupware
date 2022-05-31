package HR.DAO;

import java.sql.*;
import java.util.*;

import HR.Controller.*;
import HR.DTO.HRDataBean;
import HR.DTO.ZipcodeBean;
import HR.DTO.AttendBean;

public class HRDBBean {

	private static HRDBBean instance = new HRDBBean();

	public static HRDBBean getInstance() {
		return instance;
	}

	private HRDBBean() {
	}

	private Connection getConnection() throws Exception {
		String jdbcDriver = "jdbc:apache:commons:dbcp:pool";
		return DriverManager.getConnection(jdbcDriver);
	}

	// imformationForm.jsp
	public HRDataBean imformation(String emp_no) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		HRDataBean imformation = null;

		try {
			conn = HRDBBean.getInstance().getConnection();
			String sql = "select * from emp where emp_no =?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, emp_no);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				imformation = new HRDataBean();
				imformation.setEmp_no(rs.getString("emp_no"));
				imformation.setEmp_name(rs.getString("emp_name"));
				imformation.setDept_code(rs.getString("dept_code"));
				imformation.setEmp_position(rs.getString("emp_position"));
				imformation.setEmp_address(rs.getString("emp_address"));
				imformation.setEmp_contact(rs.getString("emp_contact"));
				imformation.setEmp_hiredate(rs.getString("emp_hiredate"));
				imformation.setEmp_password(rs.getString("emp_password"));
			} else {
				System.out.println("검색결과가 없습니다.");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException ex) {
				}
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
		}
		return imformation;
	}

	// imformationModify.jsp
	public void Updatemember(HRDataBean imformation) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = HRDBBean.getInstance().getConnection();
			pstmt = conn.prepareStatement("update emp set emp_address=?, emp_contact=?, emp_password=? where emp_no=?");
			pstmt.setString(1, imformation.getEmp_address());
			pstmt.setString(2, imformation.getEmp_contact());
			pstmt.setString(3, imformation.getEmp_password());
			pstmt.setString(4, imformation.getEmp_no());
			pstmt.executeUpdate();

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException ex) {
				}
		}

	}

	public void Insertattend(AttendBean att) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = HRDBBean.getInstance().getConnection();
			pstmt = conn.prepareStatement("insert into attendance values(?,?,?,?,?,?,?)");
			pstmt.setString(1, att.getEmp_name());
			pstmt.setString(2, att.getEmp_no());
			pstmt.setString(3, att.getDept_name());
			pstmt.setString(4, att.getEmp_date());
			pstmt.setString(5, "");
			pstmt.setString(6, att.getLate_reason());
			pstmt.setString(7, att.getAttend_time());
			

			System.out.println(pstmt.executeUpdate());

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException ex) {
				}
		}
	}

	public void Updateattendatt(AttendBean att) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = HRDBBean.getInstance().getConnection();
			pstmt = conn.prepareStatement(
					"update attendance set emp_date=?, attend_time=?, late_reason=? where emp_no=? and emp_date = ?");
			pstmt.setString(1, att.getEmp_date());
			pstmt.setString(2, att.getAttend_time());
			pstmt.setString(3, att.getLate_reason());
			pstmt.setString(4, att.getEmp_no());
			pstmt.setString(5, att.getEmp_date());
			pstmt.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			if (conn != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
		}
	}

	public void Updategetoff(AttendBean att) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = HRDBBean.getInstance().getConnection();
			pstmt = conn.prepareStatement(
					"update attendance set getoff_time=? where emp_no=? and emp_date = ?");
			pstmt.setString(1, att.getGetoff_time());
			pstmt.setString(2, att.getEmp_no());
			pstmt.setString(3, att.getEmp_date());
			pstmt.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			if (conn != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
		}
	}
	// attendenceForm.jsp
	public List<AttendBean> Attendcheck(String emp_no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<AttendBean> attList = null;

		try {
			conn = getConnection();
			String query = "select * from attendance where emp_no =?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, emp_no);
			rs = pstmt.executeQuery();
			attList = new ArrayList();
			while(rs.next()) {
				AttendBean att = new AttendBean();
				att.setEmp_name(rs.getString("emp_name"));
				att.setEmp_no(rs.getString("emp_no"));
				att.setDept_name(rs.getString("dept_name"));
				att.setEmp_date(rs.getString("emp_date"));
				att.setAttend_time(rs.getString("attend_time"));
				att.setGetoff_time(rs.getString("getoff_time"));
				att.setLate_reason(rs.getString("late_reason"));
				attList.add(att);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException ex) {
				}
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
		}
		return attList;
	}

	public boolean checkcommute(String emp_no, String date) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean result = false;

		try {
			conn = HRDBBean.getInstance().getConnection();
			String sql = "select * from attendance where emp_no =? and emp_date = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, emp_no);
			pstmt.setString(2, date);

			rs = pstmt.executeQuery();
			if (rs.next()) {
					result = true;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException ex) {
				}
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
		}
		return result;
	}
}