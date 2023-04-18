<%@page import="ex01.MemberDAO"%>
<%@page import="ex01.MemberDTO"%>
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
		memberDTO.setPasswd(request.getParameter("passWd"));
	
		if(MemberDAO.getInstance().loginMember(memberDTO)){
			session.setAttribute("memberId", request.getParameter("memberId"));
			session.setMaxInactiveInterval(60*10);	
%>
	<script>
		alert("logged in");
		location.href = "00_main.jsp";
	</script>
<%
	}
	else{
%>
	<script>		
		alert("check your ID or PASSWORD");
		history.go(-1);
	</script>
<%	
	}
	
%>
</body>
</html>