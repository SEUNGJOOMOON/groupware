package HRmag.DAO;
import java.sql.*;


import javax.sql.*;

import HRmag.DTO.AttendanceDataBean;
import HRmag.DTO.HRmagDataBean;

import javax.naming.*;
import java.util.*;

public class AttendanceDBBean {

	private static AttendanceDBBean instance = new AttendanceDBBean();

	public static AttendanceDBBean getInstance() {
		return instance;
	}

	private AttendanceDBBean() {
	}

	private Connection getConnection() throws Exception {
		String jdbcDriver = "jdbc:apache:commons:dbcp:pool";
		return DriverManager.getConnection(jdbcDriver);
	}

	   public int getAttendanceCount() throws Exception {
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			int x = 0;
			
			try {
				con = getConnection();
				pstmt = con.prepareStatement("select count(*) from attendance");
				rs=pstmt.executeQuery();
				if(rs.next()) {
					x=rs.getInt(1);
				}
				}catch(Exception e) {
					e.printStackTrace();
				} finally {
		            if (rs != null) try { rs.close(); } catch(SQLException ex) {}
		            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
		            if (con != null) try { con.close(); } catch(SQLException ex) {}
			}
				return x;
		}

		public List getAttendanceList(int startRow, int endRow) throws Exception {
				Connection con = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				List attendanceList = null;
				try {
					con = getConnection();
					pstmt = con.prepareStatement(
							"select emp_no, emp_name, dept_name, emp_date, attend_time, getoff_time, late_reason,r " +
					        "from (select emp_no, emp_name, emp_date, dept_name, attend_time, getoff_time, late_reason,rownum r " +
							"from (select emp_no, emp_name, emp_date, dept_name, attend_time, getoff_time, late_reason "+
					        "from attendance order by emp_no) order by emp_no desc) where r >=? and r<=?");
					pstmt.setInt(1, startRow);
					pstmt.setInt(2, endRow);
					rs = pstmt.executeQuery();

					if (rs.next()) {
						attendanceList = new ArrayList(endRow);
					
						while (rs.next()) {
							AttendanceDataBean attendanceArticle = new AttendanceDataBean();
							attendanceArticle.setEmp_no(rs.getString("emp_no"));
							attendanceArticle.setEmp_name(rs.getString("emp_name"));
							attendanceArticle.setDept_name(rs.getString("dept_name"));
							attendanceArticle.setEmp_date(rs.getString("emp_date"));
							attendanceArticle.setAttend_time(rs.getString("attend_time"));
							attendanceArticle.setGetoff_time(rs.getString("getoff_time"));
							attendanceArticle.setLate_reason(rs.getString("late_reason"));
							attendanceList.add(attendanceArticle);
					}
					}
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
					if (con != null)
						try {
							con.close();
						} catch (SQLException ex) {
						}
				}
				return attendanceList;

		}
	}

