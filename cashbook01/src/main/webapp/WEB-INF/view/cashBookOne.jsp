<%@page import="dao.CashbookDao"%>
<%@page import="vo.Cashbook"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	int cashbookNo = Integer.parseInt(request.getParameter("cashbookNo"));
	CashbookDao cashbookDao = new CashbookDao();
	Cashbook cashbook = cashbookDao.selectCashBookOne(cashbookNo);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>cashBookOne</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
	<h1>CashBookOne</h1>
	
		<table border="1">
			<tr>
				<td>cashbookNo</td>
				<td><%=cashbook.getCashbookNo()%></td>
			</tr>
			<tr>
				<td>cashDate</td>
				<td><%=cashbook.getCashDate()%></td>
			</tr>
			<tr>
				<td>kind</td>
				<td><%=cashbook.getKind()%></td>
			</tr>
			<tr>
				<td>cash</td>
				<td><%=cashbook.getCash()%></td>
			</tr>
			<tr>
				<td>memo</td>
				<td><%=cashbook.getMemo()%></td>
			</tr>
			<tr>
				<td>updateDate</td>
				<td><%=cashbook.getUpdateDate()%></td>
			</tr>
			<tr>
				<td>createDate</td>
				<td><%=cashbook.getCreateDate()%></td>
			</tr>
		
		</table>
	<a href="<%=request.getContextPath()%>/cashBookListByMonthController" type ="button">리스트</a>	
	<a href="<%=request.getContextPath()%>/updateCashBookController?cashbookNo=<%=cashbook.getCashbookNo()%>" type ="button">수정</a>
	<a href="<%=request.getContextPath()%>/deleteCashBookController?cashbookNo=<%=cashbook.getCashbookNo()%>" type ="button">삭제</a>
	
</body>
</html>