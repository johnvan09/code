<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2023/6/15
  Time: 19:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改密码</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
</head>
<body>
<%@include file="commons/menu.jsp" %>
<div class="container">
    <div class="panel panel-info">
        <div class="panel-heading">
            <h3 class="panel-title">
                修改密码
            </h3>
        </div>
        <div class="panel-body" style="padding: 20px;">
            <form action="${pageContext.request.contextPath}/Admin/ChangeAdminPasswordServlet" role="form" id="changePasswordForm" method="post">
                <div class="form-group">
                    <label for="oldPassword">当前密码：</label>
                    <input type="password" class="form-control" name="oldPassword" id="oldPassword"
                           placeholder="请输入当前密码">
                </div>
                <div class="form-group">
                    <label for="newPassword">新密码：</label>
                    <input type="password" class="form-control" name="newPassword" id="newPassword"
                           placeholder="请输入新密码">
                </div>
                <div class="form-group">
                    <label for="confirmPassword">确认密码：</label>
                    <input type="password" class="form-control" name="confirmPassword" id="confirmPassword"
                           placeholder="请再次输入新密码">
                </div>
                <p class="text-danger">${error}</p>
                <button type="submit" class="btn btn-default">提交</button>
            </form>
        </div>
    </div>
</div>
<%@include file="commons/footer.jsp" %>
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.validate.min.js"></script>
<script src="${pageContext.request.contextPath}/js/messages_zh.js"></script>
<script>
    $().ready(function () {
        $('#changePasswordForm').validate({
            rules: {
                oldPassword: 'required',
                newPassword: {
                    required: true,
                    minlength: 6
                },
                confirmPassword: {
                    required: true,
                    equalTo: '#newPassword'
                }
            },
            messages: {
                confirmPwd: {
                    equalTo: '两次密码输入不一至！'
                }
            },
            errorElement: "em",
            errorPlacement: function (error, element) {
                // 给错误元素增加`text-danger` class 样式
                error.addClass("text-danger");

                // Add `has-feedback` class to the parent div.form-group
                // in order to add icons to inputs
                element.parents(".col-sm-5").addClass("has-feedback");

                if (element.prop("type") === "checkbox") {
                    error.insertAfter(element.parent("label"));
                } else {
                    error.insertAfter(element);
                }

                // Add the span element, if doesn't exists, and apply the icon classes to it.
                if (!element.next("span")[0]) {
                    $("<span class='glyphicon glyphicon-remove form-control-feedback'></span>").insertAfter(element);
                }
            }
        });
    });
</script>
</body>
</html>

