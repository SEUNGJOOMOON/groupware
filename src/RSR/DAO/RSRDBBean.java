package RSR.DAO;

import java.sql.*;
import java.text.SimpleDateFormat;

import javax.sql.*;
import RSR.DTO.RSRDataBean;
import javax.naming.*;
import java.util.*;

public class RSRDBBean {
	private static RSRDBBean instance = new RSRDBBean();

	public static RSRDBBean getInstance() {
		return instance;
	}

	private RSRDBBean() {
	}

	private Connection getConnection() throws Exception {
		String jdbcDriver = "jdbc:apache:commons:dbcp:pool";
		return DriverManager.getConnection(jdbcDriver);
	}

	public void insertReserveContent(RSRDataBean reservecontent) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			String sql = "insert into room_reserve values(seqReserve.nextval,?,?,?,?,?,?)";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, reservecontent.getReserve_date());
			pstmt.setString(2, reservecontent.getReserve_room());
			pstmt.setString(3, reservecontent.getReserve_time());
			pstmt.setString(4, reservecontent.getReserve_name());
			pstmt.setString(5, reservecontent.getReserve_num());
			pstmt.setTimestamp(6, reservecontent.getRsr_date());
			pstmt.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
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

	public int getContentCount() throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int x = 0;

		try {
			conn = getConnection();
			pstmt = conn.prepareCall("select count(*) from room_reserve");
			rs = pstmt.executeQuery();
			if (rs.next()) {
				x = rs.getInt(1);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
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
		return x;
	}

	public List<RSRDataBean> getContents(int start, int end, int n, String searchKeyword) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List RSRList = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");

		try {
			conn = getConnection();

			pstmt = conn.prepareStatement(
									"select reserve_listnum,reserve_date,reserve_time,reserve_room,rsr_date,reserve_name,reserve_num,r "
							+ "from (select reserve_listnum,reserve_date,reserve_time,reserve_room,rsr_date,reserve_name,reserve_num,rownum r "
							+ "from (select reserve_listnum,reserve_date,reserve_time,reserve_room,rsr_date,reserve_name,reserve_num "
							+ "from room_reserve order by rsr_date desc)) where r >= ? and r <= ?");
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				RSRList = new ArrayList(end);

				do {
					RSRDataBean content = new RSRDataBean();
					content.setReserve_listnum(rs.getString("reserve_listnum"));
					content.setReserve_date(rs.getString("reserve_date"));
					content.setReserve_room(rs.getString("reserve_room"));
					content.setReserve_time(rs.getString("reserve_time"));
					content.setRsr_date(rs.getTimestamp("rsr_date"));
					content.setReserve_name(rs.getString("reserve_name"));
					content.setReserve_num(rs.getString("reserve_num"));
					RSRList.add(content);
				} while (rs.next());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
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
		return RSRList;
	}

	public int SearchCount(String search, String searchn) {
		PreparedStatement pstmt = null;
		Connection conn = null;
		ResultSet rs = null;

		int x = 0;

		try {
			conn = getConnection();
			String sql = "select count(*) from room_reserve where " + searchn + " LIKE '%" + search + "%'";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				x = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
				try {
					conn.close();
				} catch (Exception e) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (Exception e) {
				}
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
				}
		}
		return x;
	}

	public List<RSRDataBean> Search(String search, String pageNum, String searchn) throws Exception {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List RSRList = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
		try {
			int pageSize = 10;
			int currentPage = Integer.parseInt(pageNum);
			int startRow = (currentPage * 10) - 9;
			int endRow = currentPage * pageSize;
			int count = 0;
			int number = 0;
			conn = getConnection();

			pstmt = conn.prepareStatement("select reserve_listnum, reserve_date, reserve_time, reserve_room, rsr_date, reserve_name, reserve_num, r "
							+ "from (select reserve_listnum, reserve_date, reserve_time, reserve_room, rsr_date, reserve_name, reserve_num, rownum r "
							+ "from (select reserve_listnum, reserve_date, reserve_time, reserve_room, rsr_date, reserve_name, reserve_num "
							+ "from room_reserve where " + searchn + " like '%" + search + "%' order by rsr_date desc)) where r >= ? and r <= ?");
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);

			rs = pstmt.executeQuery();

			RSRList = new ArrayList();

			while (rs.next()) {
				RSRDataBean content = new RSRDataBean();
				
				content.setReserve_listnum(rs.getString("reserve_listnum"));
				content.setReserve_date(rs.getString("reserve_date"));
				content.setReserve_room(rs.getString("reserve_room"));
				content.setReserve_time(rs.getString("reserve_time"));
				content.setRsr_date(rs.getTimestamp("rsr_date"));
				content.setReserve_name(rs.getString("reserve_name"));
				content.setReserve_num(rs.getString("reserve_num"));

				RSRList.add(content);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
				try {
					conn.close();
				} catch (Exception e) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (Exception e) {
				}
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
				}
		}
		return RSRList;
	}

	public void deleteRSRConetent(String reserve_listnum, String reserve_num) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String contentsnum = "";
		try {
			conn = getConnection();
			
			pstmt = conn.prepareStatement("delete from room_reserve where reserve_listnum = ? and reserve_num = ?");
			pstmt.setString(1, reserve_listnum);
			pstmt.setString(2, reserve_num);
			
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
}