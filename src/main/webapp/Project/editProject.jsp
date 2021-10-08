<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	import="java.util.List,Project.ProjectBean" %>


	<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>編輯計畫內容</title>
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
	</head>

	<body>
	<h2>編輯計畫</h2>
		<form action="/Gather/ProjectServlet" method="POST">
			<jsp:useBean id="ProjectBean" class="Project.ProjectBean" scope="session" />
			
			<table>
				<tr>
					<td>計畫編號:</td>
					<td>
						<jsp:getProperty name="ProjectBean" property="pID" />
					</td>
				</tr>
				<tr>
					<td>計畫名稱:</td>
					<td><input type="text" name="editPName" id="editPName"
					value="<jsp:getProperty name="ProjectBean" property="pName" />">
					<span id="idsp1"></span>
					</td>
				</tr>
				<tr>
					<td>計畫目標金額:</td>
					<td><input type="text" name="editPTarget" id="editPTarget"
					value="<jsp:getProperty name="ProjectBean" property="pTarget" />"><span id="idsp2"></span>
					</td>
				</tr>
				<tr>
					<td>計畫開始時間:</td>
					<td><input type="datetime-local" name="editPSDate" id="editPSDate"
					value="<%=ProjectBean.getpSDate().replace(" ", "T") %>"><span id="sppSDate"></span>
				
					</td>
				</tr>
				<tr>
					<td>計畫描述:</td>
					<td><textarea  clos="10" rows="20" name="editPDescribe" id="editPDescribe" ><jsp:getProperty name="ProjectBean" property="pDescribe" /></textarea><span id="idsp3"></span>
					</td>
				</tr>
			</table>
<div class="divButton">
			<button type="submit" id="editProject" name="save" class="btn btn-primary btn-lg">存檔</button>
</div>

		</form>
		<script>
            document.getElementById("editPName").addEventListener("blur", checkPName);
            document.getElementById("editPTarget").addEventListener("blur", checkPTarget);
            document.getElementById("editPDescribe").addEventListener("blur", checkPDescribe);

            function checkPName() {
                let sp1 = document.getElementById("idsp1");
                let thePName = document.getElementById("editPName").value;
                if (thePName == "") {
                    sp1.innerHTML = "<img src='/Gather/Project/Images/error.png'>不可空白，請輸入";
                }
                else {
                    sp1.innerHTML = "<img src='/Gather/Project/Images/correct.png'>檢查正確";
                }
            }
            function checkPDescribe() {
                let sp3 = document.getElementById("idsp3");
                let thePDescribe = document.getElementById("editPDescribe").value;
                if (thePDescribe == "") {
                    sp3.innerHTML = "<img src='/Gather/Project/Images/error.png'>不可空白，請輸入";
                }
                else {
                    sp3.innerHTML = "<img src='/Gather/Project/Images/correct.png'>檢查正確";
                }
            }


            function checkPTarget() {
                let reg = /^\+?[1-9][0-9]*$/;
                let sp2 = document.getElementById("idsp2");
                let thePTarget = document.getElementById("editPTarget").value;
                if (thePTarget == "") {
                    sp2.innerHTML = "<img src='/Gather/Project/Images/error.png'>不可空白，請輸入";
                }
                else if (thePTarget.length >= 1) {
                    if (!reg.test(thePTarget)) {
                        sp2.innerHTML = "<img src='/Gather/Project/Images/error.png'>請輸入正確數值";
                    }
                    else {
                        sp2.innerHTML = "<img src='/Gather/Project/Images/correct.png'>檢查正確";
                    }
                }

            }

        </script>
	</body>

	</html>