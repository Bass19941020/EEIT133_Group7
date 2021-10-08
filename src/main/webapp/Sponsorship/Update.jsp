<%@page import="java.security.interfaces.RSAKey"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
response.setContentType("text/html;charset=UTF-8");
response.setHeader("Cache-Control", "no-cache"); // HTTP 1.1
response.setHeader("Pragma", "no-cache"); // HTTP 1.0  nocache不准暫存
response.setDateHeader("Expires", -1); // Prevents caching at the proxy server
%>
<html>
<head>
<meta charset="UTF-8">
<title>更新訂單</title>
<!-- <link rel="stylesheet" href="/Gather/style.css"> -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU"
	crossorigin="anonymous">
<style>
.btn-outline-light {
	color: #5B4B00;
	background-color: #cebfa0;
	border-color: #5B4B00;
}

.btn-outline-light:hover, .btn-outline-light:focus, .btn-outline-light:active:hover
	{
	color: #4b4b2a;
	background-color: #caca92;
	border-color: #c9c969;
}

body {
	background-color: rgba(214, 204, 191, 0.767);
	line-height: 50px;
}

h2 {
	background-color: whitesmoke;
	color: rgb(110, 104, 96);
}

tr {
	text-align: center;
}

td {
	background-color: whitesmoke;
	;
}
</style>
</head>
<body>
	<center>
		<form action="/Gather/PaymentServlet" method="post">
			<jsp:useBean id="paymentRead" class="Sponsorship.PaymentBean"
				scope="session" />
			<h2>更新訂單</h2>
			<table cellspacing="1" cellpadding="3" width="50%">
				<tr>
					<td>訂單編號:</td>
					<td><input text="text" name="updateid"
						value="<jsp:getProperty  name="paymentRead" property="orderNumber" />"></td>

				</tr>
				<tr>
					<td>贊助者:</td>
					<td><input text="text" name="updatename"
						value="<jsp:getProperty  name="paymentRead" property="sponsorName" />">
					</td>
				</tr>
				<tr>
					<td>專案編號:</td>

					<td><input text="text" name="updatepid"
						value="<jsp:getProperty name="paymentRead" property="projectID" />"></td>
				</tr>
				<tr>
					<td width="150">專案名稱:</td>

					<td><input text="text" name="updatepname"
						value="<jsp:getProperty name="paymentRead" property="projectName" />"></td>
				</tr>
				<tr>
					<td>贊助總額:</td>

					<td><input text="text" name="updateamount"
						value="<jsp:getProperty name="paymentRead" property="amount" />"></td>
				</tr>

			</table>
			<button type="submit" name="updateconfirm"
				class="btn btn-outline-light">確認</button>
			<button type="button" name="back" class="btn btn-outline-light"
				onclick="javascript:history.back(-1);">返回</button>
			<button type="button" name="main" class="btn btn-outline-light"
				onclick="javascript:history.back(-1);"
				onclick="window.location='/Gather/Sponsorship/CRUD.html'">返回首頁</button>
		</form>
	</center>
</body>
</html>