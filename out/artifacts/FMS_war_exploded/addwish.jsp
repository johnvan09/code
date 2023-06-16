<%@ page import="cdp.fms.dao.CategoryDao" %>
<%@ page import="cdp.fms.dao.Impl.CategoryDaoImpl" %>
<%@ page import="java.util.List" %>
<%@ page import="cdp.fms.model.Category" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>添加心愿</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/main.css">
    <link rel="stylesheet" href="./css/bootstrap-datepicker3.min.css">
</head>
<body>
<%@include file="commons/header.jsp" %>
<div class="container">
    <div class="container">
        <div class="panel panel-info">
            <div class="panel-heading">
                <h3 class="panel-title">
                    添加心愿信息
                </h3>
            </div>
            <div class="panel-body" style="padding: 20px;">
                <form action="addWishServlet" class="form-horizontal" role="form" method="post"
                >

                    <div class="form-group">
                        <label for="wname" class="col-sm-2 control-label">心愿名：</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="wname" id="wname"
                                   placeholder="请输入心愿名" value="${param.wname}">
                        </div>
                    </div>


                    <div class="form-group">
                        <label for="allmoney" class="col-sm-2 control-label">心愿总金额：</label>
                        <div class="col-sm-3">
                            <input type="number" min="0" class="form-control" name="allmoney" id="allmoney"
                                   placeholder="请输入金额"
                                   value="${param.money}">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="wcdate" class="col-sm-2 control-label">创建时间：</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="wcdate" id="wcdate" placeholder="请输入创建时间"
                                   value="${param.wcdate}">
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
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/bootstrap-datepicker.min.js"></script>
<script src="js/bootstrap-datepicker.zh-CN.min.js"></script>
<script>
    $().ready(function () {
        // 设置日期选择器
        $('#wcdate').datepicker({
            language: 'zh-CN', //语言
            clearBtn: true,//清除按钮
            format: "yyyy-mm-dd"//日期格式
        });
    });
</script>
</body>
</html>

