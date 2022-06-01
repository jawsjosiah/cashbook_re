<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="dao.HashtagDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	/*
	String kind = request.getParameter("kind");
	System.out.println(kind + " <-- kind (tagListByIncomeExpenditure)");	

	HashtagDao hashtagDao = new HashtagDao(); 
	*/
	
	List<Map<String, Object>> list = (List<Map<String,Object>>)request.getAttribute("list");	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>tagListByKind</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
	<h1>수입/지출 검색</h1>
		<div class="row container-fluid p-3 my-3 border ">
			<table class="table table-bordered">
			    <tr>
			        <th>kind</th>
			        <th>tag</th>
			        <th>cnt</th>
			        <th>ranking</th>
			    </tr>
			    <%
			        for(Map<String,Object> map : list) {
			    %>
			            <tr>
			                <td><%=map.get("kind") %></td>
			                <td><%=map.get("tag") %></td>
			                <td><%=map.get("cnt") %></td>
			                <td><%=map.get("ranking") %></td>
			            </tr>
			    <%
			        }
			    %>
			</table>
		</div>
</body>
</html>