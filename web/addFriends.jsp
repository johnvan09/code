<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2023/6/15
  Time: 0:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>添加好友</title>
</head>
<body>
<h1>添加好友</h1>
<form method="get" action="addFriendServlet">
    <input type="text" name="account" placeholder="输入要添加的账号">
    <button type="submit">搜索</button>
</form>
<div id="result">
    <% if (request.getAttribute("success") != null && (Boolean) request.getAttribute("success")) { %>
    <p>添加成功！</p>
    <% } else if (request.getAttribute("success") != null && !(Boolean) request.getAttribute("success")) { %>
    <p>添加失败，请检查输入的账户名是否正确。</p>
    <% } %>
</div>
</body>
</html>
