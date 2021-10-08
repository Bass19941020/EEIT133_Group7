<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="Member.MemberBean"%>
<!DOCTYPE html>
<%
String stayTime="3";
String URL="/Gather/Index.jsp";
String content=stayTime+";URL="+URL;
response.setHeader("REFRESH",content); %>
<html>
<head>
<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
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
<meta charset="UTF-8">
<title>登入成功</title>
</head>
<body>


<% 
MemberBean memberData = (MemberBean) session.getAttribute("memberData");
%>
<script>
Swal.fire({
	  position: 'center',
	  icon: 'success',
	 <%--title: 'Your work has been saved', --%>
	  title: <% out.print(
	 "'"+memberData.getStatus()+":" + memberData.getName()+ "您好!"+
	 "<br>3秒後自動為您跳回到首頁"+"'");%>,
	  showConfirmButton: false,
	  timer: 1500
	})
</script>
</body>
</html>