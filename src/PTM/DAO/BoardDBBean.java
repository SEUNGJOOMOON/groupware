package PTM.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import PTM.DTO.BoardSerchbean;
import PTM.DTO.BoardDataBean;
import PTM.DTO.BoardSerchbean;

public class BoardDBBean {
	private static BoardDBBean instance = new BoardDBBean();

	public static BoardDBBean getInstance() {
		return instance;
	}

	private BoardDBBean() {
	}

	private Connection getConnection() throws Exception {
		String jdbcDriver = "jdbc:apache:commons:dbcp:pool";
		return DriverManager.getConnection(jdbcDriver);
	}

	public void insertdata(BoardDataBean data) {

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();
			String sql = "insert into partner values(seq_partner.nextval,?,?,?,?,?,?,?)";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, data.getPartner_contact());
			pstmt.setString(2, data.getPartner_address());
			pstmt.setString(3, data.getPartner_reprecent());
			pstmt.setString(4, data.getPartner_companyname());
			pstmt.setString(5, data.getPartner_contractdate());
			pstmt.setString(6, data.getPartner_tradeitem());
			pstmt.setString(7, data.getPartner_image1());
			
			pstmt.executeUpdate();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		finally {
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

	public int getConut() {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int x = 0;

		try {
			conn = getConnection();

			pstmt = conn.prepareStatement("select count(*) from partner");

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

	public List<BoardDataBean> listboard(int start, int end) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List dblist = null;

		try {
			conn = getConnection();

			pstmt = conn.prepareStatement("select partner_num, partner_contact,partner_address,partner_reprecent,partner_companyname,partner_contractdate,partner_tradeitem,partner_image1,r "
					+ "from (select partner_num, partner_contact,partner_address,partner_reprecent,partner_companyname,partner_contractdate,partner_tradeitem,partner_image1,rownum r "
					+ "from (select partner_num, partner_contact,partner_address,partner_reprecent,partner_companyname,partner_contractdate,partner_tradeitem,partner_image1 "
					+ "from partner order by partner_num desc)) where r >= ? and r <= ?");

			pstmt.setInt(1, start);
			pstmt.setInt(2, end);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				dblist = new ArrayList(end);

				do {
					BoardDataBean db = new BoardDataBean();

					db.setPartner_contact(rs.getString("partner_contact"));
					db.setPartner_contractdate(rs.getString("partner_contractdate"));
					db.setPartner_reprecent(rs.getString("partner_reprecent"));
					db.setPartner_companyname(rs.getString("partner_companyname"));
                    db.setPartner_image1(rs.getString("partner_image1"));
                    db.setPartner_contact(rs.getString("partner_contact"));
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

	public BoardDataBean getview(String partner_companyname) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardDataBean bro = null;

		try {
			conn = getConnection();

			String sql = "select * from partner where partner_companyname = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, partner_companyname);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				bro = new BoardDataBean();
				bro.setPartner_contact(rs.getString("partner_contact"));
				bro.setPartner_address(rs.getString("partner_address"));
				bro.setPartner_companyname(rs.getString("partner_companyname"));
				bro.setPartner_tradeitem(rs.getString("partner_tradeitem"));
				bro.setPartner_reprecent(rs.getString("partner_reprecent"));
				bro.setPartner_contractdate(rs.getString("partner_contractdate"));
				bro.setPartner_image1(rs.getString("partner_image1"));
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

	public int SearchCount(String find) {

		PreparedStatement pstmt = null;
		Connection conn = null;
		ResultSet rs = null;
		int x = 0;

		try {
			conn = getConnection();

			String sql = "select count(*) from partner where partner_companyname LIKE '%" + find + "%'";

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

	public List<BoardDataBean> Search(String find, String pageNum) throws Exception {

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

			pstmt = conn.prepareStatement("select partner_num, partner_contact,partner_address,partner_reprecent,partner_companyname,partner_contractdate,partner_tradeitem,partner_image1,r "
					+ "from (select partner_num, partner_contact,partner_address,partner_reprecent,partner_companyname,partner_contractdate,partner_tradeitem,partner_image1,rownum r "
					+ "from (select partner_num, partner_contact,partner_address,partner_reprecent,partner_companyname,partner_contractdate,partner_tradeitem,partner_image1 "
					+ "from partner where partner_companyname like '%" + find + "%' order by partner_num desc)) where r >= ? and r <= ?");
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);

			rs = pstmt.executeQuery();

			ptmlist = new ArrayList();

			BoardDataBean bro = new BoardDataBean();

			while (rs.next()) {

				bro.setPartner_contact(rs.getString("partner_contact"));
				bro.setPartner_contractdate(rs.getString("partner_contractdate"));
				bro.setPartner_reprecent(rs.getString("partner_reprecent"));
				bro.setPartner_companyname(rs.getString("partner_companyname"));
                bro.setPartner_image1(rs.getString("partner_image1"));
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

}
