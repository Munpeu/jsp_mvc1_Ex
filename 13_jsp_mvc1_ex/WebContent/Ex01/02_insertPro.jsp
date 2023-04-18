<%@page import="ex01.MemberDAO"%>
<%@page import="ex01.MemberDTO"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
	
	request.setCharacterEncoding("utf-8");

	MemberDTO memberDTO = new MemberDTO();

	memberDTO.setMemberId(request.getParameter("memberId"));
	memberDTO.setPasswd(request.getParameter("passwd"));
	memberDTO.setName(request.getParameter("name"));
	// 가입하려는 정보를 넘겨주고  통보를 받음 (True(가입완료)OR False(가입불가))
	boolean isJoin = MemberDAO.getInstance().insertMember(memberDTO);
	// 
	
	// 가입완료 문구 ,가입실패 문구
	if(isJoin){
	%>
		<script>
		 alert("SUCCESS!!");
		 location.href="00_main.jsp";
		</script>
	<%
	}
	else{
	%>
		<script>
		 alert("failed.");
		 history.go(-1);
		</script>
	<%	
		
		
	}
	


%>




</body>
</html>