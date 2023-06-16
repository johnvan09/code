<%--
  Created by IntelliJ IDEA.
  User: zhao
  Date: 2021/12/15
  Time: 14:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>系统管理端登陆</title>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
</head>
<body id="login">
<div class="container">
    <div class="form row">
        <form class="form-horizontal col-sm-offset-3 col-md-offset-3" id="login_form" method="post"
              action="${pageContext.request.contextPath}/Admin/adminLoginServlet">

            <h3 class="form-title">登陆到财务系统管理端</h3>
            <div class="col-sm-9 col-md-9">
                <div class="form-group">
                    <i class="fa fa-user fa-lg"></i>
                    <input class="form-control required" type="text" placeholder="请输入账户" name="account"
                           autofocus="autofocus" maxlength="20"/>
                </div>
                <div class="form-group">
                    <i class="fa fa-lock fa-lg"></i>
                    <input class="form-control required" type="password" placeholder="请输入密码" name="password"
                           maxlength="10"/>
                </div>
                <div class="form-group">
                    <c:if test="${not empty error}">
                        <p class="alert-danger">${error}</p>
                    </c:if>
                </div>
                <div class="form-group">
                    <input type="submit" class="btn btn-success pull-right" value="登陆"/>
                </div>
            </div>
        </form>
    </div>
</div> <!-- /container -->
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</body>
</html>
