package HRmag.DAO;

import java.sql.*;


import javax.sql.*;

import HRmag.DTO.HRmagDataBean;
import HRmag.DTO.HRmagUserBean;

import javax.naming.*;
import java.util.*;

public class HRmagDBBean {

	private static HRmagDBBean instance = new HRmagDBBean();

	public static HRmagDBBean getInstance() {
		return instance;
	}

	private HRmagDBBean() {
	}

	private Connection getConnection() throws Exception {
		String jdbcDriver = "jdbc:apache:commons:dbcp:pool";
		return DriverManager.getConnection(jdbcDriver);
	}

	public void insertList(HRmagDataBean hrList) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement("insert into emp(emp_no, emp_name, emp_position, dept_code, emp_contact, emp_hiredate, emp_address, emp_profileimg, emp_password) values(?||emp_seq.nextval,?,?,?,?,?,?,?,?)");
			pstmt.setString(1, hrList.getEmp_no());
			pstmt.setString(2, hrList.getEmp_name());
			pstmt.setString(3, hrList.getEmp_position());
			pstmt.setString(4, hrList.getDept_code());
			pstmt.setString(5, hrList.getEmp_contact());
			pstmt.setString(6, hrList.getEmp_hiredate());
			pstmt.setString(7, hrList.getEmp_address());
			pstmt.setString(8, hrList.getEmp_profileimg());
			pstmt.setString(9, hrList.getEmp_password());
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
			if (con != null)
				try {
					con.close();
				} catch (SQLException ex) {
				}
		}
	}
	
	public int getListCount() throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int x = 0;
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement("select count(*) from emp");
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

	public List getList(int start, int end) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List hrList = null;
	
		try {
			con = getConnection();
        
			pstmt = con.prepareStatement(
					"select emp_password, emp_no, emp_name, emp_position, emp_contact, dept_code, dept_name, emp_hiredate,emp_address,r " +
			        "from (select emp_password, emp_no, emp_name, emp_position, emp_contact, dept_code, dept_name, emp_hiredate, emp_address,rownum r " +
					"from (select emp_password, emp_no, emp_name, emp_position, emp_contact, e.dept_code dept_code, dept_name, emp_hiredate, emp_address "+
			        "from emp e inner join dept d on e.dept_code=d.dept_code order by emp_no) order by emp_no desc) where r >=? and r <=?");
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				hrList = new ArrayList(end);
			while (rs.next()) {
					HRmagDataBean hrInfo = new HRmagDataBean();
					hrInfo.setEmp_password(rs.getString("emp_password"));
					hrInfo.setEmp_no(rs.getString("emp_no"));
					hrInfo.setEmp_name(rs.getString("emp_name"));
					hrInfo.setDept_code(rs.getString("dept_code"));
					hrInfo.setDept_name(rs.getString("dept_name"));
					hrInfo.setEmp_position(rs.getString("emp_position"));
					hrInfo.setEmp_hiredate(rs.getString("emp_hiredate"));
					hrInfo.setEmp_contact(rs.getString("emp_contact"));
					hrInfo.setEmp_address(rs.getString("emp_address"));
					hrList.add(hrInfo);
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
		return hrList;
	}
	public int getSearchCount(String keyword) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int x = 0;
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement("select count(*) from emp where emp_name LIKE '%"+keyword+"%'");
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
	

	public HRmagUserBean LoginProcess(String empno, String password) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		HRmagUserBean user = null;
		try {
			con = getConnection();

			pstmt = con.prepareStatement("select * from emp where emp_no=? and emp_password=?");
			pstmt.setString(1, empno);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			if(rs.next()){
				user = new HRmagUserBean();
				user.setEmpNo(rs.getString("emp_no"));
				user.setEmpName(rs.getString("emp_name"));
				user.setDeptCode(rs.getString("dept_code"));
				user.setEmpPosition(rs.getString("emp_position"));
				user.setEmpAddress(rs.getString("emp_address"));
				user.setEmpContact(rs.getString("emp_contact"));
				user.setEmpProfileImg(rs.getString("emp_profileimg"));
				user.setEmpHireDate(rs.getString("emp_hiredate"));
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
		return user;
	}

	public List getSearchList(int start, int end, String keyword) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List hrList = null;
		try {
			con = getConnection();

			pstmt = con.prepareStatement(
					"select emp_no, emp_name, emp_position, emp_contact, emp_hiredate,emp_address, dept_code, dept_name,r " +
			        "from (select emp_no, emp_name, emp_position, emp_contact, emp_hiredate, emp_address,dept_code, dept_name, rownum r " +
					"from (select emp_no, emp_name, emp_position, emp_contact, emp_hiredate, emp_address, e.dept_code dept_code, dept_name "+
			        "from emp e inner join dept d on e.dept_code=d.dept_code where emp_name like '%"+keyword+"%' order by emp_no desc)) where r >=? and r<=?");
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				hrList = new ArrayList(end);
			
				while (rs.next()) {
					HRmagDataBean hrArticle = new HRmagDataBean();
					hrArticle.setEmp_no(rs.getString("emp_no"));
					hrArticle.setEmp_name(rs.getString("emp_name"));
					hrArticle.setDept_code(rs.getString("dept_code"));
					hrArticle.setDept_name(rs.getString("dept_name"));
					hrArticle.setEmp_position(rs.getString("emp_position"));
					hrArticle.setEmp_hiredate(rs.getString("emp_hiredate"));
					hrArticle.setEmp_contact(rs.getString("emp_contact"));
					hrArticle.setEmp_address(rs.getString("emp_address"));
					hrList.add(hrArticle);
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
		return hrList;
	}
	  public HRmagDataBean updateGetList(String empNo) throws Exception {
	        Connection conn = null;
	        PreparedStatement pstmt = null;
	        ResultSet rs = null;
	        HRmagDataBean hrList=null;
	        try {
	            conn = getConnection();

	            pstmt = conn.prepareStatement(
	            "select emp_password, emp_no, emp_name, emp_position, emp_address, emp_contact, e.dept_code, dept_name from emp e inner join dept d on e.dept_code=d.dept_code where emp_no=?");
	            pstmt.setString(1, empNo);

	            rs = pstmt.executeQuery();

	            if (rs.next()) {
	                hrList = new HRmagDataBean();
	                hrList.setEmp_password(rs.getString("emp_password"));
	                hrList.setEmp_no(rs.getString("emp_no"));
	                hrList.setEmp_name(rs.getString("emp_name"));
	                hrList.setEmp_position(rs.getString("emp_position"));
	                hrList.setEmp_address(rs.getString("emp_address"));
	                hrList.setEmp_contact(rs.getString("emp_contact"));
	                hrList.setDept_code(rs.getString("dept_code"));
	                hrList.setDept_name(rs.getString("dept_name"));
	               
	}
	        } catch(Exception ex) {
	            ex.printStackTrace();
	        } finally {
	            if (rs != null) try { rs.close(); } catch(SQLException ex) {}
	            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
	            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
	        }
	return hrList;
	    }

    public void updatehrList(HRmagDataBean hrList) throws Exception {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs= null;
        String sql="";
      
        try {
            conn = getConnection();
   
      sql="update emp set emp_name=?, dept_code=?, emp_position=?, emp_contact =?, emp_address=?, emp_password=? where emp_no=?";
                pstmt = conn.prepareStatement(sql);
               
                pstmt.setString(1, hrList.getEmp_name());
                pstmt.setString(2, hrList.getDept_code());
                pstmt.setString(3, hrList.getEmp_position());
                pstmt.setString(4, hrList.getEmp_contact());
                pstmt.setString(5, hrList.getEmp_address());
                pstmt.setString(6, hrList.getemp_password());
                pstmt.setString(7, hrList.getEmp_no());
      

                pstmt.executeUpdate();


        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
    if (rs != null) try { rs.close(); } catch(SQLException ex) {}
            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
        }
        }
    
    public void listDelete(String emp_no) throws Exception {
    	Connection con = null;
    	PreparedStatement pstmt = null;
    	try {
    		con = getConnection();
    		pstmt = con.prepareStatement("delete from emp where emp_no=?");
    		pstmt.setString(1, emp_no);
    		pstmt.executeUpdate();
    		
    	}catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
            if (con != null) try { con.close(); } catch(SQLException ex) {}
        }
        }
    
    }
