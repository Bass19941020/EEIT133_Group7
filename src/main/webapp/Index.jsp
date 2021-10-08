<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="Member.MemberBean"%>
<!DOCTYPE html>
<html>
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU"
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="/Gather/style.css">
<!-- CSS only -->
<meta charset="UTF-8">
<title>首頁</title>
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<div class="container-fluid">
			<a class="navbar-brand" href="/Gather/Index.jsp">Gather</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="#">探索(Null)</a></li>
					<li class="nav-item"><a class="nav-link"
						href="/Gather/Project/menu.html">提案</a></li>
					<li class="nav-item"><a class="nav-link"
						href="/Gather/Ad/AdForm.html">廣告刊登</a></li>
					<li class="nav-item"><a class="nav-link"
						href="/Gather/Activity/RegisterForm2.jsp">活動</a></li>
					
					
					<!-- 
			  <li class="nav-item">
				<a class="nav-link disabled">Disabled</a>
			  </li>
			   -->
				</ul>
				<form class="d-flex">
					<!--搜尋列 用不到 先註銷
			  <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
					55 Object obj = session.getAttribute("memberData");
					MemberBean memberData= (MemberBean)session.getAttribute("memberData");
			  -->

					<%
					MemberBean memberData= (MemberBean)session.getAttribute("memberData");
					if (memberData == null) {
					%>
					<a href="/Gather/Member/Register.jsp">
						<button class="btn btn-outline-secondary" type="button">註冊</button>
					</a> 
					<a href="/Gather/Member/Login.jsp">
						<button class="btn btn-outline-danger" type="button">登入</button>
					</a>
					
					
					<%
					} else {
						
						if(memberData.getStatus().equals("管理員")){%>
						
						
						<div class="nav-item dropdown">
					<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
						role="button" data-bs-toggle="dropdown" aria-expanded="false">
							管理員選項 </a>
						<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
							<li><a class="dropdown-item" href="/Gather/Member/Admin.jsp">會員管理</a></li>
							<li><a class="dropdown-item" href="/Gather/Sponsorship/CRUD.html">贊助金流管理</a></li>
							<li><hr class="dropdown-divider"></li>
							<!-- 
							
							<li><a class="dropdown-item" href="#">一個空位</a>
							</li>
							 -->
						</ul>
					</div>
						<%-- --%>
						<%}%>
					
					<div class="nav-item dropdown">
					<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
						role="button" data-bs-toggle="dropdown" aria-expanded="false">
							會員中心 </a>
						<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
							<li><a class="dropdown-item" href="/Gather/Member/MemberCenter.jsp">進入中心</a></li>
							<li><a class="dropdown-item" href="/Gather/Member/MemberSetting.jsp">修改密碼</a></li>
							<li><hr class="dropdown-divider"></li>
							<!-- 
							
							<li><a class="dropdown-item" href="#">一個空位</a>
							</li>
							 -->
						</ul>
					</div>
					
					<!--  -->
					<a href="/Gather/Member/Logout.jsp">
						<button class="btn btn-outline-danger" type="button">登出</button>
					</a>


					<%
					}
					%>
					
					
	





					<ul class="navbar-nav me-auto mb-2 mb-lg-0">
						<li class="nav-item"></li>
					</ul>

				</form>
			</div>
		</div>
	</nav>
	<!-- JavaScript Bundle with Popper -->
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ"
		crossorigin="anonymous"></script>




</body>
</html>