<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import = "Member.MemberBean,java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>全部會員資料</title>
</head>
<link rel="stylesheet" type="text/css" href="/Gather/style.css">
<style>
body{
  text-align: center;
}

table{
margin-left:auto; 
margin-right:auto;
}
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

<%--

td, th {
  border: 1px solid #dddddd;
  text-align: left;
  padding: 8px;
}

tr:nth-child(even) {
  background-color: #dddddd;
}
 --%>
<body>
<a href=<%="/Gather/Member/Admin.jsp" %>>回到管理員選項</a><br>

<%
MemberBean Data= (MemberBean)request.getSession(true).getAttribute("queryAllData");
Iterator<Integer> id = Data.getArray_Id().iterator();
Iterator<String> name = Data.getArray_Name().iterator();
Iterator<String> act = Data.getArray_Account().iterator();
Iterator<String> pwd = Data.getArray_Password().iterator();
Iterator<String> sts = Data.getArray_Status().iterator();
%>


<table>
  <tr>
    <th>會員編號</th>
    <th>姓名</th>
    <th>身份</th>
    <th>帳號</th>
    <th>密碼</th>
  </tr>
  
<%while(id.hasNext()) { %>  
  <tr>
    <td><%= id.next()%></td>
    <td><%= name.next()%></td>
    <td><%= act.next()%></td>
    <td><%= pwd.next()%></td>
    <td><%= sts.next()%></td>
  </tr>
<%} %>

</table>


</body>
</html>