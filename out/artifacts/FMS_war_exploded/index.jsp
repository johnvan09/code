<%--
  Created by IntelliJ IDEA.
  User: zhao
  Date: 2021/12/15
  Time: 14:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="css/style.css"/>
    <title>登录 | 财务管理系统</title>
</head>
<body>
<div class="login-container">
    <h1>财务管理系统</h1>

    <div class="connect">
        <p>作为一个程序员，也要管理好自己的财务</p>
    </div>

    <form action="LoginServlet" method="post" id="loginForm">
        <div>
            <input type="text" name="account" class="username" placeholder="用户名" autocomplete="off"/>
        </div>
        <div>
            <input type="password" name="password" class="password" placeholder="密码"
                   oncontextmenu="return false" onpaste="return false"/>
        </div>
        <div>
            <p class="alert-danger">${error}</p>
        </div>
        <button id="submit" type="submit">登 陆</button>
    </form>

    <a href="register.jsp">
        <button type="button" class="register-tis">还有没有账号？</button>
    </a>

</div>
</body>
<script src="https://libs.baidu.com/jquery/1.10.2/jquery.min.js"></script>

<!--背景图片自动更换-->
<script src="js/supersized.3.2.7.min.js"></script>
<script src="js/supersized-init.js"></script>
<!--表单验证-->
<script src="js/jquery.validate.min.js"></script>
<script src="js/common.js"></script>
</html>

