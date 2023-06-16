<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>新闻信息</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
</head>
<body>
<%@include file="commons/menu.jsp" %>
<div class="container">
    <div class="container">
        <div class="panel panel-info">
            <div class="panel-heading">
                <h3 class="panel-title">
                    修改新闻信息
                </h3>
            </div>
            <div class="panel-body" style="padding: 20px;">
                <form action="${pageContext.request.contextPath}/Admin/changeNewServlet" class="form-horizontal" role="form" method="post"
                      enctype="multipart/form-data">
                    <div class="form-group">
                        <label for="account" class="col-sm-2 control-label">新闻名：</label>
                        <div class="col-sm-10">
                            <input type="text"  class="form-control" name="naame" id="account"
                                   placeholder="请输入新闻名" value="${sessionScope.news.nnmae}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="username" class="col-sm-2 control-label">作者：</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="writer" id="username"
                                   value="${sessionScope.news.writer}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="gender" class="col-sm-2 control-label">内容：</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="info" id="gender"
                                   value="${sessionScope.news.ninfo}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="photo" class="col-sm-2 control-label">新闻图片：</label>
                        <div class="col-sm-10">
                            <input type="file" class="form-control" name="photo" id="photo" placeholder="请选择新闻图片"
                                   accept="image/jpg,image/jpeg,image/png" value="${sessionScope.news.nimg}">
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
</body>
</html>
