<%@page import="dao.MemberDao"%>
<%@page import="vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	Member member = new Member();
	member.setMemberId((String)request.getAttribute("memberId"));
	member.setMemberPw((String)request.getAttribute("memberPw"));
	member.setCreateDate((String)request.getAttribute("createDate"));
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>memberOne</title>
</head>
<body>
	<h1>memberOne</h1>
	
	<table border="1">
		<tr>
			<td>memberId</td>
			<td><%=member.getMemberId() %></td>
		</tr>
		<tr>
			<td>memberPw</td>
			<td><%=member.getMemberPw() %></td>
		</tr>
		<tr>
			<td>createDate</td>
			<td><%=member.getCreateDate() %></td>
		</tr>
	</table>
	
	<!-- 넘길 값 확인 필요함 -->
	<a href="<%=request.getContextPath() %>/updateMemberPwController">비밀번호 수정</a>
	<a href="<%=request.getContextPath() %>/deleteMemberController">회원 정보 삭제</a>
</body>
</html>