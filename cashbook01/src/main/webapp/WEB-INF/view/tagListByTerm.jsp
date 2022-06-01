<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	List<Map<String, Object>> list = (List<Map<String,Object>>)request.getAttribute("list");	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>tagListByTerm</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
	<h1>날짜별 검색</h1>
		<div class="row container-fluid p-3 my-3 border ">
			<table class="table table-bordered">
			    <tr>
			        <th>kind</th>
			        <th>tag</th>
			        <th>cnt</th>
			        <th>ranking</th>
			        <th>cashDate</th>
			    </tr>
			    <%
			        for(Map<String,Object> map : list) {
			    %>
			            <tr>
			                <td><%=map.get("kind") %></td>
			                <td><%=map.get("tag") %></td>
			                <td><%=map.get("cnt") %></td>
			                <td><%=map.get("ranking") %></td>
			                <td><%=map.get("cashDate") %></td>
			            </tr>
			    <%
			        }
			    %>
			</table>
		</div>
</body>
</html>