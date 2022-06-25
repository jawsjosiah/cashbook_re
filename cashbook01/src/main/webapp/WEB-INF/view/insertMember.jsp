<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>insertMember</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
	<h1>회원 가입</h1>
	<form action="<%=request.getContextPath()%>/insertMemberController" method="post">
		<table border="1">
			<tr>
				<td>아이디 등록</td>
				<td><input type="text" name="memberId"></td>
			</tr>
			<tr>
				<td>비밀번호 등록</td>
				<td><input type="password" name="memberPw"></td>
			</tr>
		</table>
		<button type="submit" class="btn btn-outline-info">회원가입</button>
	</form>
</body>
</html>