<%@page import="ex02.MemberDAO"%>
<%@page import="ex02.MemberDTO"%>
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
		memberDTO.setPassWd(request.getParameter("passwd"));
		memberDTO.setName(request.getParameter("name"));
	
		if(MemberDAO.getinstance().getJoin(memberDTO)){
	%>
			<script>
				alert("SUCCESS!!");
			</script>
	<%
		}
		else{	
	%>
			<script>
				alert("failed");
			</script>
	<%
		}
	%>

</body>
</html>