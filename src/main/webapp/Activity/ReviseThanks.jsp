<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="false"%>

<%
response.setContentType("text/html;charset=UTF-8");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <link rel="stylesheet" href="/Gather/style.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style>
    h1{text-align: center;}
</style>
<title>修改報名資訊完成</title>
</head>
<body>
<form action="/Gather/ActivityController" method="post">
<h1>修改成功</h1>

<center>
<input type="submit" name="return" value="返回首頁" >
</center>
</form>
</body>
</html>