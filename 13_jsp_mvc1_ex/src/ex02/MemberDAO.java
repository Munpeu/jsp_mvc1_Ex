package ex02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberDAO {

	private MemberDAO() {}
	private static MemberDAO instance = new MemberDAO();
	
	public static MemberDAO getinstance() {
		return instance;
	}
	
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	

	
public void getConnection() {
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		String url = "jdbc:mysql://localhost:3306/login_ex?serverTimezone=UTC";
		String user    = "root";
		String password  = "3akycka";
		
		conn = DriverManager.getConnection(url,user,password);
		
	}catch (Exception e) {
		e.printStackTrace();
	}
}
	
public void getClose() {
		try {if(rs!=null) rs.close();} catch (SQLException e) {e.printStackTrace();}
		try {if(pstmt!=null) pstmt.close();} catch (SQLException e) {e.printStackTrace();}
		try {conn.close();} catch (SQLException e) {e.printStackTrace();}	
}
	
public boolean getJoin(MemberDTO memberDTO) {
	boolean isJoin = false;
		try {
			getConnection();
			
			pstmt = conn.prepareStatement("select * from member where member_id=?");
			pstmt.setString(1, memberDTO.getMemberId());
			rs = pstmt.executeQuery();
			
			if(!rs.next()) {
				pstmt = conn.prepareStatement("insert into member values(?,?,?,now())");
				pstmt.setString(1, memberDTO.getMemberId());
				pstmt.setString(2, memberDTO.getPassWd());
				pstmt.setString(3, memberDTO.getName());
				pstmt.executeUpdate();
				isJoin = true;
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			getClose();
		}
	
		
	return isJoin;
}

public boolean login(String memberId, String passwd) {
	boolean isLogin = false;
	try {
		getConnection();
		
		pstmt = conn.prepareStatement("select * from member where member_id=? and passwd=?");
		pstmt.setString(1, memberId);
		pstmt.setString(2, passwd);
		rs = pstmt.executeQuery();
		
		if(rs.next()) {
			isLogin = true;
		}

	}catch (Exception e) {
		e.printStackTrace();
	}finally {
		getClose();
	}
	
	return isLogin;
}






















	
}
