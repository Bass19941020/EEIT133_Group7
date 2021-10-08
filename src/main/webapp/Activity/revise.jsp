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
<title>活動報名資料修改</title>
<style>
    td{
        font-family: "微軟正黑體";
    }
    h2{
        text-align: center;
    }
</style>
</head>
<body>
<h2>
請輸入身分證字號查詢原報名資料
</h2>
<form action="/Gather/ActivityController" method="post">
<table  cellspacing="2" cellpadding="1" border="1" width="100%">

<tr>
    <td>身分證字號:</td>
    <td><input type="text" name="id" size="20" maxlength="10"></td>
</tr>


</table>
<center>
<input type="submit" name="revise1" value="送出">
</center>
</form>
</body>
</html>