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
    <title>添加新闻</title>
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
                    添加账单信息
                </h3>
            </div>
            <div class="panel-body" style="padding: 20px;">
                <form action="${pageContext.request.contextPath}/Admin/addNewServlet" class="form-horizontal" role="form" method="post"
                      enctype="multipart/form-data">

                    <div class="form-group">
                        <label for="bname" class="col-sm-2 control-label">新闻名：</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="bname" id="bname"
                                   placeholder="请输入新闻名">
                        </div>
                    </div>


                    <div class="form-group">
                        <label for="money" class="col-sm-2 control-label">作者：</label>
                        <div class="col-sm-3">
                            <input type="text" min="0" class="form-control" name="writer" id="money"
                                   placeholder="请输入作者"
                            >
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="way" class="col-sm-2 control-label">内容：</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="way" id="way" placeholder="请输入新闻内容"
                            >
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="photo" class="col-sm-2 control-label">新闻图片：</label>
                        <div class="col-sm-10">
                            <input type="file" class="form-control" name="photo" id="photo" placeholder="请选择新闻照片"
                                   accept="image/jpg,image/jpeg,image/png" value="${sessionScope.user.photo}">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="bcdate" class="col-sm-2 control-label">创建时间:</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="bcdate" id="bcdate"
                                   placeholder="请选择您的创建时间">
                        </div>

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
<script>
    $().ready(function () {
        // 设置日期选择器
        $('#bcdate').datepicker({
            language: 'zh-CN', //语言
            clearBtn: true,//清除按钮
            format: "yyyy-mm-dd"//日期格式
        });
    });
</script>
</body>
</html>

