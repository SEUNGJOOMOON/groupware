<%@ page contentType="text/xml; charset=UTF-8"%>
<%@page import="java.sql.*"%>
<%@page import="java.util.*"%>
<%@page import="RSR.DTO.*"%>
<%
	Class.forName("oracle.jdbc.driver.OracleDriver");
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String RSRList = "";
	request.setCharacterEncoding("UTF-8");

	String calendar1 = request.getParameter("calendar");
	String time1 = request.getParameter("time");

	try {
		conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "scott", "tiger");

		pstmt = conn.prepareStatement("select reserve_room from room_reserve where reserve_date=? and reserve_time=?");
		pstmt.setString(1, calendar1);
		pstmt.setString(2, time1);

		rs = pstmt.executeQuery();
		while (rs.next()) {
			//reserve_room.add(rs.getString("reserve_room"));
			RSRList += rs.getString("reserve_room") +";";
			System.out.println(RSRList);
		}
	} catch (SQLException e) {
		e.printStackTrace();
	
	}
%>
<reserve> 
<info><%=RSRList%> 
</info> 
</reserve>