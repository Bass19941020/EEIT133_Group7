<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>selectProject</title>
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
	<h2>查詢計畫:</h2>
	<form action="/Gather/ProjectServlet" method="GET">
		<table>
			<tr>
				<td>計畫ID:</td>
				<td><input type="text" name="pID" size="10" maxlength="10"></td>
			</tr>
		</table>
		<div class="divButton">
			<button type="submit" id="submit" name="selectProject"
				class="btn btn-primary btn-lg">查詢</button>
			<button type="submit" id="backHome" name="backHome"
				class="btn btn-primary btn-lg">取消</button>
		</div>
	</form>
</body>

</html>