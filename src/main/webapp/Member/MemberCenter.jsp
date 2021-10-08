<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="Member.MemberBean"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="/Gather/style.css">
<meta charset="UTF-8">
<title>會員中心</title>
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

img{
	width: 100px;
	height: 100px;
}
</style>
</head>
<body>
	<%-- 兩欄式網頁--%>
	<%--
	左側選項:
	提案紀錄
	贊助紀錄 --%>
	
	<a href="/Gather/Member/MemberSetting.jsp">帳號設定</a>
	<br>
	<a href="/Gather/Member/Logout.jsp">登出</a>
	<br>
	<a href="/Gather/Index.jsp">首頁</a>
	<br>

	
	
	<img src="/Gather/Member/photo/${memberData.id}.jpg"
	 alt="">


	<%--
	右側顯示:
	--%>




	您:${memberData.name}
	身分為${memberData.status}


	<%--
	個人提案狀態
	贊助過的提案狀態
	--%>
</body>
</html>