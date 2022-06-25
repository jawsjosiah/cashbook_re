<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>tagList</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
<%
	List<Map<String, Object>> list = (List<Map<String,Object>>)request.getAttribute("list");
	
%>
	<div class="row container-fluid p-3 my-3 border ">
	
		<div class="col-sm-3">
			<h1>해시태그 순위</h1>
			
				<table class="table table-bordered">
					<tr>
						<th>순위</th>
						<th>태그</th>
						<th>개수</th>
					</tr>
					<%
						for(Map<String,Object> map : list) {
					%>
							<tr>
								<td><%=map.get("rank")%></td>
								<td><a href="<%=request.getContextPath() %>/selectTagByTagController?tag=<%=map.get("tag")%>"><%=map.get("tag")%></td>
								<td><%=map.get("cnt")%></td>
							</tr>
					<%
						}
					%>
				</table>
			</div>
			
		<div class="col-sm-9">
			<h1> 수입/지출별 검색 </h1>	
				<form action="<%=request.getContextPath()%>/selectTagByKindController">
					
						<table class="table table-bordered">
							<tr>
								<td>수입/지출 선택</td>
								<td>
									<div><input type="radio" name="kind" value="수입">수입</div>
									<div><input type="radio" name="kind" value="지출">지출</div>
								</td>
							</tr>
							<tr>
								<td colspan="2">
									<button type="submit" class="btn btn-primary">검색</button>
								</td>
							</tr>
						</table>
				</form>
		
		
		
			<h1> 날짜별 검색 </h1>
				<form action="<%=request.getContextPath()%>/selectTagByTermController">
					
					<table class="table table-bordered">
							<tr>
								<td>날짜 선택</td>
								<td>
									<div>시작일 : <input type="date" name="beginDate"></div>
									<div>종료일 : <input type="date" name="endDate"></div>
								</td>
							</tr>
							<tr>
								<td colspan="2">
									<button type="submit" class="btn btn-primary">검색</button>
								</td>
							</tr>
						</table>
				</form>
		
			
		
			<div>
					<button type="button" class="btn btn-info">
						<a href="<%=request.getContextPath() %>/cashBookListByMonthController">
							<p class="text-white">가계부</p>
						</a>
					</button>
			</div>
		</div>
	</div>
</body>
</html>