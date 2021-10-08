<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.List,Project.ProjectBean"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>計畫列表</title>
<link rel="stylesheet" href="/Gather/style.css">
<style>
table {
	border: 1;
	width: 100%;
	border-spacing: 2;
}

h2 {
	text-align: center;
}

.btn-primary {
	color: #fff;
	background-color: #46A3FF;
	border-color: black;
}

.btn-primary:hover, .btn-primary:focus, .btn-primary:active:hover {
	color: #fff;
	background-color: #0080FF;
	border-color: black;
}
</style>
</head>
<body>

	<h2>計畫清單</h2>

	<%-- 
<jsp:getProperty name="projectList" property="pName" />
--%>
	<jsp:useBean id="projectList" class="java.util.ArrayList"
		scope="session" />


	<form action="/Gather/ProjectServlet" method="POST">
		<table>
			<thead>
				<tr>
					<td>計畫ID</td>
					<td>計畫名稱</td>
					<td>計畫目標金額</td>
					<td>計畫描述</td>
				</tr>
			</thead>
			<tbody>
				<%
				for (int i = 0; i < projectList.size(); i++) {
					ProjectBean p = (ProjectBean) projectList.get(i);
				%>
				<tr>
					<td><%=p.getpID()%></td>
					<td><%=p.getpName()%></td>
					<td><%=p.getpTarget()%></td>
					<td><%=p.getpDescribe()%></td>
				</tr>
				<%
				}
				%>

			</tbody>
		</table>
		<button type="submit" id="backHome" name="backHome"
			class="btn btn-primary btn-lg">回首頁</button>
	</form>

</body>
</html>