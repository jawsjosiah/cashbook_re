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
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
	<h1>태그별 상세 보기 모음</h1>
		<div class="row container-fluid p-3 my-3 border ">
				<table class="table table-bordered">
				    <tr>
				        <th>tag</th>
				        <th>cashDate</th>
				        <th>kind</th>
				        <th>cash</th>
				        <th>memo</th>
				    </tr>
				    <%
				        for(Map<String,Object> map : list) {
				    %>
				            <tr>
				                <td><%=map.get("tag") %></td>
				                <td><%=map.get("cashDate") %></td>
				                <td><%=map.get("kind") %></td>
				                <td><%=map.get("cash") %></td>
				                <td><%=map.get("memo") %></td>
				            </tr>
				    <%
				        }
				    %>
				</table>
		</div>
</body>
</html>