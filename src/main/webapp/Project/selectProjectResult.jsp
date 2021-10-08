<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.List,Project.ProjectBean"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>計畫內容</title>
<link rel="stylesheet" href="/Gather/style.css">
<style>
h2 {
	text-align: center;
}

form {
	width: 70%;
	margin: 0 auto;
	text-align: center;
}

table {
	border: 1;
	width: 100%;
	border-spacing: 2;
}

input {
	font-size: 16px;
	width: 50%;
}

tr {
	text-align: left;
}

span {
	margin: 10px;
}

.divButton {
	margin: 10px;
	text-align: right;
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
	<form action="/Gather/ProjectServlet" method="POST">
		<jsp:useBean id="ProjectBean" class="Project.ProjectBean"
			scope="session" />

		<table>
			<tr>
				<td>計畫編號:</td>
				<td><jsp:getProperty name="ProjectBean" property="pID" /></td>
			</tr>
			<tr>
				<td>計畫名稱:</td>
				<td><jsp:getProperty name="ProjectBean" property="pName" /></td>
			</tr>
			<tr>
				<td>計畫目標金額:</td>
				<td><jsp:getProperty name="ProjectBean" property="pTarget" />
				</td>
			</tr>
			<tr>
				<td>計畫開始時間:</td>
				<td><jsp:getProperty name="ProjectBean" property="pSDate" />
				</td>
			</tr>
			<tr>
				<td>計畫描述:</td>
				<td><jsp:getProperty name="ProjectBean" property="pDescribe" />
				</td>
			</tr>
		</table>

		<div class="divButton">
			<button type="submit" id="editProject" name="editProject"
				class="btn btn-primary btn-lg">編輯</button>
			<button type="submit" id="deleteProject" name="deleteProject"
				class="btn btn-primary btn-lg">刪除</button>
			<button type="submit" id="backHome" name="backHome"
				class="btn btn-primary btn-lg">回首頁</button>
		</div>
	</form>
</body>

</html>