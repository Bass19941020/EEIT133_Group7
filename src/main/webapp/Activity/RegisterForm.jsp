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
    <link rel="stylesheet" href="/Gather/style.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>班級報名註冊</title>
<style>
    h2{text-align: center;}
</style>
</head>
<body>
<h2>報名資料登入</h2>
<form action="/Gather/ActivityController" method="post">
<table  cellspacing="2" cellpadding="1" border="1" width="100%">
<tr>
    <td>姓名:</td>
    <td> 
        
        <input type="text" name="name" id="id_Name">
        <span id="id_SpanName"></span><br>
        <label class="a">不可空白，至少兩個字以上，必須全部為中文字</label></td>
</tr>
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
    
</tr>
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
</table>
<center>
<input type="submit" name="submit" value="送出">
</center>
</form>
<script src="check.js">
  
  

  document.getElementById("id_Name").addEventListener('blur',
()=>{
        //輸入
        let object_Name = document.getElementById("id_Name");
        let value_Name = object_Name.value;
        //輸出
        let object_SpanName = document.getElementById("id_SpanName");

        if(value_Name!=""&&value_Name.length>=2){
            for (let index = 0; index < value_Name.length; index++) {
                let unicode = value_Name.charCodeAt(index);
                if (unicode>=0x4e00 && unicode<=0x9fff) {
                    object_SpanName.className="ok";
                    return object_SpanName.innerHTML="格式正確";
                }else{
                    object_SpanName.className="no";
                    return object_SpanName.innerHTML="<img src='img/error.png'>必須為中文";
                }
            }
        }else if(value_Name!==""){
            object_SpanName.className="no";
            return object_SpanName.innerHTML="<img src='img/error.png'>至少兩個字";
        }else{
            object_SpanName.className="no";
            return object_SpanName.innerHTML="<img src='img/error.png'>*必填";
        }
    });
    
                      
                    
</script>

</body>
</html>