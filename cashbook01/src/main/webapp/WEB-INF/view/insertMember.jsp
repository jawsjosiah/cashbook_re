<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>InsertMember</title>
</head>
<body>
	<h1>회원 가입</h1>
	<form action="<%=request.getContextPath()%>/InsertMemberController" method="post">
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
		<button type="submit">회원가입</button>
	</form>
</body>
</html>