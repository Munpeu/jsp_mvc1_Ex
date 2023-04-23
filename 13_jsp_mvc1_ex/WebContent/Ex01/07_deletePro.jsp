<%@page import="ex01.MemberDTO"%>
<%@page import="ex01.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
		// delete한 계정 다시 로그인 됨 버그		

		request.setCharacterEncoding("utf-8");
		
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setMemberId(request.getParameter("memberId"));
		memberDTO.setPasswd(request.getParameter("passwd"));
		boolean isDelete = MemberDAO.getInstance().deleteMember(memberDTO);
		if(isDelete == true){
			session.invalidate();
%>			
			<script>
			 alert("Your account has been deleted successfully");
			 location.href="00_main.jsp";
			</script>		
<%			
		}
		else{
%>			
		<script>
		 alser("Check your ID or PASSWORD");
		 history.go(-1);
		</script>		
<%			
		}
%>

</body>
</html>