<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="Member.MemberBean"%>
<%
response.setContentType("text/html;charset=UTF-8");
response.setHeader("Cache-Control", "no-cache"); // HTTP 1.1
response.setHeader("Pragma", "no-cache"); // HTTP 1.0
response.setDateHeader("Expires", -1); // Prevents caching at the proxy server
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="/Gather/style.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<%
Object obj = session.getAttribute("memberData");
if (obj == null) {
	out.print("你沒有登入，頁面跳轉中");
	String content = "5" + ";URL=" + "/Gather/Member/Login.jsp";
	response.setHeader("REFRESH", content);
} else {
	MemberBean memberData = (MemberBean) session.getAttribute("memberData");
	if (memberData.getStatus().equals("管理員")) {
%>
<title>管理會員</title>
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
	<h2>管理會員</h2>
	<form action="/Gather/MemberController" method="post">
		<table cellspacing="2" cellpadding="1" border="1" width="100%">
			<tr>
				<td>編號:</td>
				<td><input type="text" name="id" size="10" maxlength="10"></td>
			</tr>

			<tr>
				<td>姓名:</td>
				<td><input type="text" name="name" size="10" maxlength="10"></td>
			</tr>

			<tr>
				<td>帳號:</td>
				<td><input type="text" name="account" size="10" maxlength="10"></td>
			</tr>

			<tr>
				<td>密碼:</td>
				<td><input type="text" name="password" size="10" maxlength="10"></td>
			</tr>

			<tr>
				<td>身分:</td>
				<td><select name="status">
					<option >請選擇身分</option>
					<option value="一般會員">一般會員</option>
					<option value="廠商">廠商</option>
					<option value="管理員">管理員</option>
				</select></td>
			</tr>

		</table>
		<center>
			<%
			if(session.getAttribute("status") != null){
				String code = (String)session.getAttribute("status");
				if(code.equals("seccess")){
					out.print("成功!<br>");
				}else if(code.equals("fail")){
					out.print("失敗!<br>");
				}
				session.removeAttribute("status");
			}
			%>
			<input type="submit" name="registerAdmin" value="新增會員"><br>
			<input type="submit" name="queryAll" value="查詢全部會員"><br>
			<input type="submit" name="querySingle" value="使用編號，查詢單筆會員"><br>
			<input type="submit" name="changeStatus" value="使用編號&姓名，更新會員身分"><br>
			<input type="submit" name="delete" value="使用編號，刪除會員"><br>
			<a href="/Gather/Index.jsp">回到首頁</a>
		</center>
	</form>
</body>
</html>

<%
} else {
out.print("你沒有權限訪問這裡，5秒後為您導向會員中心");
String content = "5" + ";URL=" + "/Gather/Member/MemberCenter.jsp";
response.setHeader("REFRESH", content);
}

}
%>

