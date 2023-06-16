<%--
  Created by IntelliJ IDEA.
  User: zhao
  Date: 2021/12/20
  Time: 17:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>页面跳转</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/main.css">
</head>
<body>
<div class="text-center">
    <h3 class="text-info">${message} 页面将在5秒内跳转....</h3>
    <p>您也可以直接<a href="${page}">跳转</a></p>
</div>

<script>
    function jump(){
        window.location.href='${page}';
    }
    setTimeout(jump, 5000);
</script>
</body>
</html>
