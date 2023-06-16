<%--
  Created by IntelliJ IDEA.
  User: zhao
  Date: 2021/12/18
  Time: 17:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>系统出现异常（${pageContext.errorData.statusCode}）</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
</head>
<body>
<div>
    <h4 class="alert-danger">系统出现异常（状态码：${pageContext.errorData.statusCode}）</h4>
    <table class="table table-bordered table-hover">
        <tr>
            <td style="vertical-align: middle;" class="col-md-2 text-center"><b>URI:</b></td>
            <td>${pageContext.errorData.requestURI}</td>
        </tr>
        <tr>
            <td style="vertical-align: middle;" class="text-center"><b>状态码:</b></td>
            <td>${pageContext.errorData.statusCode}</td>
        </tr>
        <tr>
            <td style="vertical-align: middle;" class="text-center"><b>异常消息:</b></td>
            <td><pre>${pageContext.exception.localizedMessage}</pre></td>
        </tr>
        <tr>
            <td style="vertical-align: middle;" class="text-center"><b>堆栈轨迹:</b></td>
            <td>
                <code>
                <c:forEach var="trace" items="${pageContext.exception.stackTrace}">
                    ${trace}
                </c:forEach>
                </code>
            </td>
        </tr>
    </table>
</div>
</body>
</html>
