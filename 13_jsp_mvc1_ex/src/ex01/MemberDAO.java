package ex01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberDAO {

	private MemberDAO() {}
	private static MemberDAO instance = new MemberDAO();
	public static MemberDAO getInstance() {
		return instance;
	}
	public static void setInstance(MemberDAO instance) {
		MemberDAO.instance = instance;
	}
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public void getConnection() {
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url ="jdbc:mysql://localhost:3306/login_ex?serverTimezone=UTC";
			String user = "root";
			String password = "3akycka";
			
			conn = DriverManager.getConnection(url,user,password);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void getClose() {
			if(rs !=null)try {rs.close();} catch (SQLException e) {e.printStackTrace();}
			if(pstmt !=null)try {pstmt.close();} catch (SQLException e) {e.printStackTrace();}
			if(conn !=null)try {conn.close();} catch (SQLException e) {e.printStackTrace();}	
	}

	
	public boolean insertMember(MemberDTO memberDTO) {
		boolean isJoin = false;
		try {
			getConnection();
			pstmt=conn.prepareStatement("select * from member where member_id=?");
			pstmt.setString(1, memberDTO.getMemberId());
			rs = pstmt.executeQuery();
			
			if(!rs.next()) {
				pstmt = conn.prepareStatement("insert into member values(?,?,?,now())");
				pstmt.setString(1, memberDTO.getMemberId());
				pstmt.setString(2, memberDTO.getPasswd());
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
	
	public boolean loginMember(MemberDTO memberDTO) {
		boolean isLogin = false;
		
		try {
			getConnection();
			pstmt = conn.prepareStatement("select * from member where member_id=? and passwd=? ");
			pstmt.setString(1, memberDTO.getMemberId());
			pstmt.setString(2, memberDTO.getPasswd());
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
	
	public boolean deleteMember(MemberDTO memberDTO) {
		boolean isDelete = false;
		
		try {
			getConnection();
			pstmt = conn.prepareStatement("select * from member where member_id = ? and passwd=?");
			pstmt.setString(1, memberDTO.getMemberId());
			pstmt.setString(2, memberDTO.getPasswd());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				pstmt = conn.prepareStatement("delete from member where member_id=?");
				pstmt.setString(1, memberDTO.getMemberId());
				pstmt.executeUpdate();
				isDelete = true;
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			getClose();
		}
		return isDelete;
	}
	
	
	
	public boolean updateMember(MemberDTO memberDTO) {
		
		boolean isUpdate = false;
		
		try {
			
			getConnection();
			pstmt = conn.prepareStatement("SELECT * FROM MEMBER WHERE MEMBER_ID = ? AND PASSWD = ?");
			pstmt.setString(1, memberDTO.getMemberId());
			pstmt.setString(2, memberDTO.getPasswd());
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				pstmt = conn.prepareStatement("UPDATE MEMBER SET NAME = ? WHERE MEMBER_ID = ?");
				pstmt.setString(1, memberDTO.getName());
				pstmt.setString(2, memberDTO.getMemberId());
				pstmt.executeUpdate();
				isUpdate = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getClose();
		}
		
		return isUpdate;
		
	}
	
}
