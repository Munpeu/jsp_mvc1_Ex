<%@page import="ex02.MemberDAO"%>
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
	
	String memberId = request.getParameter("memberId");
	String passwd = request.getParameter("passwd");
	
	if(MemberDAO.getinstance().login(memberId,passwd)){
		session.setAttribute("memberId", memberId);
%>
	<script>
	 alert("SUCCESSFUL");
	</script>
<%		
		
	}
	else{
%>
	<script>
	 alert("check ID and PASSWORD");
	</script>
<%		
	}
%>

</body>
</html>