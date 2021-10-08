<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

	<!DOCTYPE html>
	<html lang="en">

	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<title>計畫提案</title>
		<link rel="stylesheet" href="/Gather/style.css">
		<style>
		h2{
			text-align: center;
				}
        form{
        width: 70%;
        margin:0 auto;
        text-align: center;
        
        }
			table {
				border: 1;
				width: 100%;
				border-spacing: 2;
				
			}

			input {
			font-size: 16px;
				width: 50%;
			}
			tr{
			text-align: left;
			}
			
			span{
			margin: 10px;
			}
						
			.divButton{
			margin: 10px;
			text-align: right;
			}
			textarea{
			height: 200px;
			width: 565px;
			font-size:16px;
			}
			.btn-primary {
  color: #fff;
  background-color: #46A3FF;
  border-color: black;
}
.btn-primary:hover, .btn-primary:focus, .btn-primary:active:hover{
  color: #fff;
  background-color: #0080FF;
  border-color: black;
}
		</style>

<script>
		document.addEventListener("DOMContentLoaded", function () {
			var nowDate = new Date().toISOString().substring(0, 11) + "00:00";
			var test = document.getElementById("pSDate").value = nowDate;
			console.log(test);
		});
	</script>
	

	<body>
		<h2>計畫提案:</h2>
		<form action="/Gather/ProjectServlet" method="GET">

			<table>
				<tr>
					<td>計畫名稱:</td>
					<td><input type="text" name="pName" id="pName"><span id="idsp1"></span></td>
				</tr>
				<tr>
					<td>計畫目標金額:</td>
					<td><input type="text" id="pTarget" name="pTarget"><span id="idsp2"></span></td>
				</tr>
				<tr>
					<td>計畫開始時間:</td>
					<td><input type="datetime-local" id="pSDate" name="pSDate" value=""><span id="sppSDate"></span></td>
				</tr>
				<tr>
					<td>計畫描述:</td>
					<td><textarea  id="pDescribe" name="pDescribe" clos="10" rows="20" value=""></textarea><span id="idsp3"></span></td>
				</tr>
			</table>

<div class="divButton">
			<button type="submit" id="submit" name="addProject" class="btn btn-primary btn-lg">提交</button>
			<button type="submit" id="backHome" name="backHome" class="btn btn-primary btn-lg">取消</button>
			</div>
		</form>
		
		<script>
			document.getElementById("pName").addEventListener("blur", checkPName);
			document.getElementById("pTarget").addEventListener("blur",
				checkPTarget);
			document.getElementById("pDescribe").addEventListener("blur",
				checkPDescribe);

			function checkPName() {
				let sp1 = document.getElementById("idsp1");
				let thePName = document.getElementById("pName").value;
				if (thePName == "") {
					sp1.innerHTML = "<img src='/Gather/Project/Images/error.png'>不可空白，請輸入";
				} else {
					sp1.innerHTML = "<img src='/Gather/Project/Images/correct.png'>檢查正確";
				}
			}
			function checkPDescribe() {
				let sp3 = document.getElementById("idsp3");
				let thePDescribe = document.getElementById("pDescribe").value;
				if (thePDescribe == "") {
					sp3.innerHTML = "<img src='/Gather/Project/Images/error.png'>不可空白，請輸入";
				} else {
					sp3.innerHTML = "<img src='/Gather/Project/Images/correct.png'>檢查正確";
				}
			}

			function checkPTarget() {
				let reg = /^\+?[1-9][0-9]*$/;
				let sp2 = document.getElementById("idsp2");
				let thePTarget = document.getElementById("pTarget").value;
				if (thePTarget == "") {
					sp2.innerHTML = "<img src='/Gather/Project/Images/error.png'>不可空白，請輸入";
				} else if (thePTarget.length >= 1) {
					if (!reg.test(thePTarget)) {
						sp2.innerHTML = "<img src='/Gather/Project/Images/error.png'>請輸入正確數值";
					} else {
						sp2.innerHTML = "<img src='/Gather/Project/Images/correct.png'>檢查正確";
					}
				}

			}
		</script>
	</body>


	</html>