<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
response.setContentType("text/html;charset=UTF-8");
response.setHeader("Cache-Control","no-cache"); // HTTP 1.1
response.setHeader("Pragma","no-cache"); // HTTP 1.0
response.setDateHeader ("Expires", -1); // Prevents caching at the proxy server
%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <link rel="stylesheet" href="/Gather/style.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>報名資料確認</title>
</head>
<body>
<jsp:useBean id="reg_student" class="Activity.ActivityBean" scope="session" />
<h2>
報名資料如下請確認
</h2>
<form action="/Gather/ActivityController" method="post">
<table  cellspacing="2" cellpadding="1" border="1" width="100%">
<tr bgcolor="#FFFFE1">
    <td>姓名:</td>
    <td><jsp:getProperty name="reg_student" property="name" /></td>
</tr>
<tr bgcolor="#F2F4FB">
    <td>生日:</td>
    <td>
    民國
    <jsp:getProperty name="reg_student" property="birthyear" /> 年
    <jsp:getProperty name="reg_student" property="birthmonth" />月
    <jsp:getProperty name="reg_student" property="birthday" />日    
    </td>
</tr>
<tr bgcolor="#FFFFE1">
    <td>身份證字號:</td>
    <td><jsp:getProperty name="reg_student" property="id" /></td>
</tr>
<tr bgcolor="#F2F4FB">
    <td width="150">聯絡地址:</td>
    <td><jsp:getProperty name="reg_student" property="zipcode" /> <jsp:getProperty name="reg_student" property="address" /></td>
</tr>
<tr bgcolor="#FFFFE1">
    <td>聯絡電話</td>
    <td><jsp:getProperty name="reg_student" property="phone" /></td>
</tr>
<tr bgcolor="#F2F4FB">
    <td>E-mail:</td>
    <td><jsp:getProperty name="reg_student" property="mailaddress" /></td>
</tr>
</table>
<center>
<input type="submit" name="confirm" value="確認" >
</center>
</form>
</body>
</html>