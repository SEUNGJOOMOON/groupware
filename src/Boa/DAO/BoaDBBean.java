package Boa.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import Boa.DTO.BoaDataBean;
import PTM.DTO.BoardDataBean;

public class BoaDBBean {

	private static BoaDBBean instance = new BoaDBBean();

	public static BoaDBBean getInstace() {

		return instance;
	}

	private BoaDBBean() {

	}

	private Connection getConnection() throws Exception {
		String jdbcDriver = "jdbc:apache:commons:dbcp:pool";
		return DriverManager.getConnection(jdbcDriver);
	}

	public void insertdatad(BoaDataBean boa) {

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			String sql = "insert into board(board_num,board_writerid,board_writer,board_title,board_content,board_date)values(board_seq.NEXTVAL,?,?,?,?,?)";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, boa.getBoard_writerid());
			pstmt.setString(2, boa.getBoard_writer());
			pstmt.setString(3, boa.getBoard_title());
			pstmt.setString(4, boa.getBoard_content());
			pstmt.setTimestamp(5, boa.getBoard_date());

			pstmt.executeUpdate();
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

		}

	}

	public int getConuter() {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int x = 0;

		try {
			conn = getConnection();

			pstmt = conn.prepareStatement("select count(*) from board");

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
				} catch (SQLException e) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
				}

		}
		return x;

	}

	public List<BoaDataBean> listboard(int start, int end) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List dblist = null;

		try {
			conn = getConnection();

			pstmt = conn.prepareStatement(
					"select board_num, board_writerid,board_writer,board_title,board_content,board_hit,board_date,r "
							+ "from (select board_num, board_writerid,board_writer,board_title,board_content,board_hit,board_date,rownum r "
							+ "from (select board_num, board_writerid,board_writer,board_title,board_content,board_hit,board_date "
							+ "from board order by board_date desc)) where r >= ? and r <= ?");

			pstmt.setInt(1, start);
			pstmt.setInt(2, end);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				dblist = new ArrayList(end);

				do {
					BoaDataBean db = new BoaDataBean();
					db.setBoard_num(rs.getString("board_num"));
					db.setBoard_writerid(rs.getString("board_writerid"));
					db.setBoard_writer(rs.getString("board_writer"));
					db.setBoard_title(rs.getString("board_title"));
					db.setBoard_content(rs.getString("board_content"));
					db.setBoard_hit(rs.getInt("board_hit"));
					db.setBoard_date(rs.getTimestamp("board_date"));
					dblist.add(db);
				} while (rs.next());
			}

		} catch (Exception e) {
			System.out.println("디비에러 : " + e.getMessage());
		} finally {

			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
				}

		}

		return dblist;

	}

	public BoaDataBean getview(String board_title) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoaDataBean bro = null;

		try {
			conn = getConnection();

			String sql = "update board set board_hit=board_hit+1 where board_title = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board_title);
			pstmt.executeUpdate();

			pstmt = conn.prepareStatement("select * from board where board_title = ?");
			pstmt.setString(1, board_title);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				bro = new BoaDataBean();
				bro.setBoard_num(rs.getString("board_num"));
				bro.setBoard_writerid(rs.getString("board_writerid"));
				bro.setBoard_writer(rs.getString("board_writer"));
				bro.setBoard_title(rs.getString("board_title"));
				bro.setBoard_content(rs.getString("board_content"));
				bro.setBoard_hit(rs.getInt("board_hit"));
				bro.setBoard_date(rs.getTimestamp("board_date"));

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
		return bro;

	}

	public int SearchCount(String search, String sel) {

		PreparedStatement pstmt = null;
		Connection conn = null;
		ResultSet rs = null;
		int x = 0;

		try {
			conn = getConnection();

			String sql = "select count(*) from board where " + sel + " LIKE '%" + search + "%'";

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

	public List<BoaDataBean> Search(String search, String pageNum, String sel) throws Exception {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List ptmlist = null;

		try {
			int pageSize = 10;
			int currentPage = Integer.parseInt(pageNum);
			int startRow = (currentPage * 10) - 9;
			int endRow = currentPage * pageSize;
			int count = 0;
			int number = 0;
			conn = getConnection();

			pstmt = conn.prepareStatement(
					"select board_num, board_writerid,board_writer,board_title,board_content,board_hit,board_date,r "
							+ "from (select board_num, board_writerid,board_writer,board_title,board_content,board_hit,board_date,rownum r "
							+ "from (select board_num, board_writerid,board_writer,board_title,board_content,board_hit,board_date "
							+ "from board where " + sel + " like '%" + search
							+ "%' order by board_date desc)) where r >= ? and r <= ?");

			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);

			rs = pstmt.executeQuery();

			ptmlist = new ArrayList();

			while (rs.next()) {
				BoaDataBean bro = new BoaDataBean();
				bro.setBoard_num(rs.getString("board_num"));
				bro.setBoard_writerid(rs.getString("board_writerid"));
				bro.setBoard_writer(rs.getString("board_writer"));
				bro.setBoard_title(rs.getString("board_title"));
				bro.setBoard_content(rs.getString("board_content"));
				bro.setBoard_hit(rs.getInt("board_hit"));
				bro.setBoard_date(rs.getTimestamp("board_date"));

				ptmlist.add(bro);

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
		return ptmlist;
	}

	public void Delete(int board_num) throws Exception {

		PreparedStatement pstmt = null;
		Connection conn = null;
		ResultSet rs = null;
		String dbpass = "";

		try {
			conn = getConnection();

			String sql = "delete from board where board_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board_num);
			pstmt.executeUpdate();

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

	}

	public BoaDataBean updatenum(String board_num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoaDataBean db = null;

		try {
			conn = getConnection();

			pstmt = conn.prepareStatement("select * from board where board_num = ?");
			pstmt.setString(1, board_num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				db = new BoaDataBean();
				db.setBoard_num(rs.getString("board_num"));
				db.setBoard_writerid(rs.getString("board_writerid"));
				db.setBoard_writer(rs.getString("board_writer"));
				db.setBoard_title(rs.getString("board_title"));
				db.setBoard_content(rs.getString("board_content"));
				db.setBoard_hit(rs.getInt("board_hit"));
				db.setBoard_date(rs.getTimestamp("board_date"));
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
		return db;
	}

	public void updatepro(BoaDataBean bo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

	

		try {
			conn = getConnection();

			String sql2 = "update board set board_writerid=?,board_writer=?,board_title=?,board_content=? where board_num=?";
			pstmt = conn.prepareStatement(sql2);

			pstmt.setString(1, bo.getBoard_writerid());
			pstmt.setString(2, bo.getBoard_writer());
			pstmt.setString(3, bo.getBoard_title());
			pstmt.setString(4, bo.getBoard_content());
			pstmt.setString(5, bo.getBoard_num());
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
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
}