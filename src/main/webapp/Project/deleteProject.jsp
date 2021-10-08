<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

    <!DOCTYPE html>
    <html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>deleteProject</title>
        <link rel="stylesheet" href="/Gather/style.css">
    </head>

    <body>
        <h2>
            刪除計畫:
        </h2>
        <form action="/Gather/ProjectServlet" method="GET">
            <table>
            
                <tr>
                    <td>計畫名稱:</td>
                    <td><input type="text" name="pName" size="10" maxlength="10"></td>
                </tr>
            </table>
            <button type="submit" id="submit" name="deleteProject">提交</button>
        </form>
    </body>

    </html>