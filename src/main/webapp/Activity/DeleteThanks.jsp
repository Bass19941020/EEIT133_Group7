<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="false"%>

<%
response.setContentType("text/html;charset=UTF-8");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="/Gather/style.css">
<title>取消報名</title>
</head>
<body>
<form action="/Gather/ActivityController" method="post">
<center>
<h1>取消報名成功</h1>
<input type="submit" name="return" value="返回首頁" >
</center>

</form>

</body>
</html>