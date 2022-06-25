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
<title>회원 상세보기</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
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
	<a href="<%=request.getContextPath() %>/updateMemberPwController" class="btn btn-outline-primary">비밀번호 수정</a>
	<a href="<%=request.getContextPath() %>/deleteMemberController" class="btn btn-outline-danger">회원 정보 삭제</a>
</body>
</html>