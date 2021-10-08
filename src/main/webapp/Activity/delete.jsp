<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<% 
response.setContentType("text/html;charset=UTF-8");
response.setHeader("Cache-Control","no-cache"); // HTTP 1.1
response.setHeader("Pragma","no-cache"); // HTTP 1.0
response.setDateHeader ("Expires", -1); // Prevents caching at the proxy server
%>
<html>
<head>
    <link rel="stylesheet" href="/Gather/style.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>取消活動報名</title>
</head>
<body>
<header>
<form action="/Gather/ActivityController" method="post">
<h1>取消活動報名</h1>
</header>
<table  cellspacing="2" cellpadding="1" border="1" width="100%">


<tr>
    <td>身份證字號:</td>
    <td><input type="text" name="id" maxlength="10"></td>
</tr>

</table>
<center>
<input type="submit" name="delete1" value="送出">
</center>


</form>

</body>
</html>