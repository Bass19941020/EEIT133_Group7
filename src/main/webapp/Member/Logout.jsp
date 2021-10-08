<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登出</title>
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
</head>
<body>
<%session.invalidate(); %>
<%
String content = "3" + ";URL=" + "/Gather/Index.jsp";
response.setHeader("REFRESH", content); %>

<script>
Swal.fire({
	  position: 'center',
	  icon: 'success',
	 <%--title: 'Your work has been saved', --%>
	  title: <%="'您以登出，期待再度光臨!<br>3秒後自動為您跳回到首頁'"%>,
	  showConfirmButton: false,
	  timer: 1500
	})
</script>
</body>
</html>