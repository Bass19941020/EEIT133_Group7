<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/Gather/Photo" method="post" enctype="multipart/form-data">
	
		<label for="input-1">δΈε³εη</label>
		<input id="input-1" name="photo" placeholder="Text" type="file"/>
		<input type="submit" value="Submit" id="button-1"/>
	</form>
</body>
</html>