<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>完成更新</title>
<link rel="stylesheet" href="/Gather/style.css">
</head>
<body>
	<center>
		<h2>
			更新成功<br>頁面將於3秒後跳轉回首頁
		</h2>

	</center>
	<script>
		function countDown() {
			setTimeout("location.href='/Gather/Sponsorship/CRUD.html'", 3000);
		}
		countDown();
	</script>
</body>
</html>