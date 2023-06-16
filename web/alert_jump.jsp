<%--
  Created by IntelliJ IDEA.
  User: zhao
  Date: 2021/12/20
  Time: 18:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>操作提示</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/main.css">
</head>
<body>
<script>
    alert('${message}'); //显示消息
    window.location.href='${page}'; //执行页面跳转
</script>
</body>
</html>
