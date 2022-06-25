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
<title>deleteMember</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
	<h1>회원 탈퇴</h1>
	<form action="<%=request.getContextPath() %>/deleteMemberController" method="post">
		<table border="1">
			<tr>
				<td>삭제할 아이디 확인</td>
				<td><%=member.getMemberId() %></td>
			</tr>
			<tr>
				<td>비밀번호 확인</td>
				<td><input type="password" name="memberPw"></td>
			</tr>
		</table>
		<button type="submit" class="btn btn-outline-danger">회원 삭제</button>
	</form>
</body>
</html>