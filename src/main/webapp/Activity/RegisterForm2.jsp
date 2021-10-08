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

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="/Gather/style.css">
<title>Gather活動資訊</title>
<style>
    h2{
        text-align: center;
        font-family: "微軟正黑體";
        font-size: xx-large;        
    }
    input{
        font-family: "微軟正黑體";
    }
</style>
</head>
<body>
<h2>
活動報名系統
</h2>
<form action="/Gather/ActivityController" method="post">

<center>
<input type="submit" name="signup" value="報名活動">
<input type="submit" name="check" value="查詢活動報名資訊">
<input type="submit" name="delete" value="取消報名活動">
<input type="submit" name="revise" value="修改活動報名資訊">

</center>



</form>
</body>
</html>