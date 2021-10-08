<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="Member.MemberBean"%>
<!DOCTYPE html>
<%
MemberBean memberData = (MemberBean)session.getAttribute("memberData");
if (memberData==null) {
%>
<html>
<head>
<link rel="stylesheet" type="text/css" href="/Gather/style.css">
<meta charset="UTF-8">
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
body{
    background-color: rgba(214, 204, 191, 0.767);
    line-height: 50px;
}
h2{
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
<title>登入頁面</title>
</head>
<body>
	<form action="/Gather/MemberController" method="post">
		<label for="account">帳號:</label><br> 
		<input type="text" id="account" name="account"><br> 
		<label for="password">密碼:</label><br>
		<input type="text" id="password" name="password"><br>
		<br> <input type="submit" name="login" value="登入">
	</form>
	<a href="/Gather/Index.jsp">回到首頁</a>
	<%
	if (session.getAttribute("status") != null) {
		String status = (String) session.getAttribute("status");
		if (status.equals("fail")) {
			out.print("<h1>登入失敗</h1>");
			session.removeAttribute("status");
		}
	}
	%>


</body>
</html>

<%
} else {
out.print("您已經登入過了。五秒後為您自動導向會員中心");
String content = "5" + ";URL=" + "Gather/Member/MemberCenter.jsp";
response.setHeader("REFRESH", content);
}
%>