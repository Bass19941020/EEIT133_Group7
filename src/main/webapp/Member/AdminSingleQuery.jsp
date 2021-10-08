<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="Member.MemberBean"%>
<%
response.setContentType("text/html;charset=UTF-8");
response.setHeader("Cache-Control","no-cache"); // HTTP 1.1
response.setHeader("Pragma","no-cache"); // HTTP 1.0
response.setDateHeader ("Expires", -1); // Prevents caching at the proxy server
%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="/Gather/style.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>單筆查詢</title>
</head>
<body>
<h2>
單筆查詢
</h2>
<%
MemberBean data = (MemberBean)session.getAttribute("querySingleData");
%>


<form action="/Gather/MemberController" method="post">
<table  cellspacing="2" cellpadding="1" border="1" width="100%">
<tr>
    <td>編號:</td>
    <td><%= data.getId()%></td>
</tr>

<tr>
    <td>姓名:</td>
    <td><%= data.getName()%></td>
</tr>

<tr>
    <td>身分:</td>
    <td><%= data.getStatus()%></td>
</tr>

<tr>
    <td>帳號:</td>
    <td><%= data.getAccount()%></td>
</tr>

<tr>
    <td>密碼:</td>
    <td><%= data.getPassword()%></td>
</tr>


</table>
<center>
<a href=<%="/Gather/Member/Admin.jsp" %>>回到管理員選項</a><br>
</center>
</form>
</body>
</html>