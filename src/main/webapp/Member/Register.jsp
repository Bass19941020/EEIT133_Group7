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
<title>註冊會員頁面</title>
<link rel="stylesheet" type="text/css" href="/Gather/style.css">
<style>
    table{
        border-spacing: 2%;
        padding: 1%;
        border: 1% ;
        width: 100%;
    }
    
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
img{
    width:100px;
    height: 100px;
}
</style>
</style>
</head>
<body>

<h2>
簡易註冊會員
</h2>
<form action="/Gather/MemberController" method="post" enctype="multipart/form-data">
<table  >
<tr>
    <td>姓名:</td>
    <td><input type="text" id="id_Name" name="name" size="10" maxlength="10"><span id="id_SpanName"></span></td>
</tr>

<tr>
    <td>帳號:</td>
    <td><input type="text"  id="id_Act" name="account" size="10" maxlength="10"><span id="id_SpanAct"></span></td>
</tr>

<tr>
    <td>密碼:</td>
    <td><input type="text" id="id_Pwd" name="password" size="10" maxlength="10"><span id="id_SpanPwd"></span></td>
</tr>

<tr>
	<td>上傳大頭貼:</td>
	<td><img id="blah" src="#" alt="your image" /><input id="imgInp" name="photo" placeholder="Text" type="file"/></td>
</tr>





<%--
<tr>
    <td>生日:</td>
    <td>
    民國
    <input type="text" name="birthyear" size="2" maxlength="3">年
    <input type="text" name="birthmonth" size="2" maxlength="2">月
    <input type="text" name="birthday" size="2" maxlength="2">日    
    </td>
</tr>

<tr>
    <td>身份證字號:</td>
    <td><input type="text" name="id" maxlength="10"></td>
</tr
>
<tr>
    <td width="150">聯絡地址:</td>
    <td>郵遞區號 <input type="text" name="zipcode" size="3" maxlength="3"><input type="text" name="address" size="50" maxlength="50"></td>
</tr>
<tr>
    <td>聯絡電話:</td>
    <td><input type="text" name="phone" size="20"></td>
</tr>
<tr>
    <td>E-mail:</td>
    <td><input type="text" name="mailaddress" size="20" maxlength="20"></td>
</tr>
 --%>
</table>
<center>
<input type="submit" name="register" value="送出">
<a href="/Gather/Index.jsp">回到頁面</a>

</center>
</form>
<%--
<script src="/Gather/Member/js/Check.js"></script> --%>
<script>
imgInp.onchange = evt => {
	  const [file] = imgInp.files
	  if (file) {
	    blah.src = URL.createObjectURL(file)
	  }
	}
</script>
</body>
</html>