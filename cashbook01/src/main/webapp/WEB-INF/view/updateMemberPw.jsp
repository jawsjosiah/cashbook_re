<%@page import="vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	Member member = new Member();
	member.setMemberId((String)request.getAttribute("sessionMemberId"));
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>updateMemberPw</title>
</head>
<body>
	<h1>비밀 번호 변경</h1>
	<form action="<%=request.getContextPath()%>/updateMemberPwController" method="post">
	<table border="1">
		<tr>
			<td>아이디</td>
			<td><%=member.getMemberId() %></td>
		</tr>
		<tr>
			<td>변경할 비밀번호 입력</td>
			<td><input type="password" name="memberPw"></td>
		</tr>
		
	</table>
	<button type = "submit">회원 정보 변경</button>
</body>
</html>