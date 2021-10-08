<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="/Gather/style.css">
<meta charset="UTF-8">
<title>會員設定</title>
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
</head>
<body>
<h1>修改密碼</h1>
<form action="/Gather/MemberController" method="post">
  <label for="old_P">舊密碼:</label><br>
  <input type="text" name="old_P"><br>
  <label for="new_P">新密碼:</label><br>
  <input type="text" name="new_P"><br>
  <label for="again_P">再次輸入密碼:</label><br>
  <input type="text" name="again_P"><br><br>
  <input type="submit" name="changePwd" value="送出">
  <a href="/Gather/Member/MemberCenter.jsp">返回</a>
  <%
  	if(session.getAttribute("status") !=null){
  		String code = (String)session.getAttribute("status");
  		if(code.equals("seccess")){
  			out.print("成功!<br>");
  		}else if(code.equals("fail")){
  			out.print("失敗!<br>");
  		}else if(code.equals("retry")){
  			out.print("輸入錯誤，請檢查舊密碼，以及兩次輸入有沒有正確!!<br>");
  		}
  		session.removeAttribute("status");
  	}
	
  %>
</form> 
</body>
</html>