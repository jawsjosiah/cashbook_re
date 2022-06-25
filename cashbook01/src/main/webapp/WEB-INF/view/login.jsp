<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
	<h1>로그인</h1>
	<form action="<%=request.getContextPath() %>/loginController" method="post">
		<table border="1">
			<tr>
				<td>memberId</td>
				<td><input type="text" name="memberId"></td>
			</tr>
			<tr>
				<td>memberPw</td>
				<td><input type="password" name="memberPw"></td>
			</tr>
		</table>
		<button type="submit" class="btn btn-outline-secondary">로그인</button>
	</form>
	
	<a href="<%=request.getContextPath() %>/insertMemberController" class="btn btn-outline-info">
		<p>회원 가입</p>
	</a>
	
</body>
</html>