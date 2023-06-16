
<%@ page import="cdp.fms.dao.CategoryDao" %>
<%@ page import="cdp.fms.dao.Impl.CategoryDaoImpl" %>
<%@ page import="java.util.List" %>
<%@ page import="cdp.fms.model.Category" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2023/6/13
  Time: 14:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>添加分类</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-datepicker3.min.css">

</head>
<body>
<%@include file="commons/menu.jsp" %>
<div class="container">
    <div class="container">
        <div class="panel panel-info">
            <div class="panel-heading">
                <h3 class="panel-title">
                    添加分类信息
                </h3>
            </div>
            <div class="panel-body" style="padding: 20px;">
                <form action="${pageContext.request.contextPath}/Admin/addAdminServlet" class="form-horizontal" role="form" method="post">

                    <div class="form-group">
                        <label for="inorout" class="col-sm-2 control-label">收入或者支出：</label>
                        <select name="inorout" id="inorout">
                            <option value="收入">收入</option>
                            <option value="支出">支出</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <label>具体分类名<span class="text-danger">*</span></label>
                        <input type="text" class="form-control" name="category"  placeholder="请输入分类名">
                    </div>


                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <button type="submit" class="btn btn-default">确定</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<%@include file="commons/footer.jsp" %>
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap-datepicker.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap-datepicker.zh-CN.min.js"></script>
</body>
</html>



