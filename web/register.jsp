<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2023/6/9
  Time: 15:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>注册</title>
    <link rel="stylesheet" href="./css/bootstrap.min.css">
    <link rel="stylesheet" href="./css/bootstrap-datepicker3.min.css">
    <link rel="stylesheet" href="./css/main.css">
</head>
<body>
<div class="container container-small">
    <h1>注册
        <small>已有帐号，<a href="index.jsp">登录</a></small>
    </h1>
    <form action="RegisterServlet" method="post" id="signupForm">
        <div class="form-group">
            <label>账号<span class="text-danger">*</span></label>
            <input type="text" class="form-control" name="account" placeholder="请输入账户" value="${param.account}">
            <%if(request.getAttribute("error_account") != null){%>
            <p class="text-danger"><%=request.getAttribute("error_account")%></p>
            <%}%>
        </div>
        <div class="form-group">
            <label>昵称<span class="text-danger">*</span></label>
            <input type="text" class="form-control" name="nickname" placeholder="请输入名称" value="${param.nickname}">
            <c:if test="error_nickname">
                <p class="text-danger">${error_username}</p>
            </c:if>
        </div>
        <div>
            <label>性别<span class="text-danger">*</span></label>
            <input type="text" class="form-control" name="gender" placeholder="请输入性别" value="${param.gender}">
        </div>
        <div class="form-group">
            <label>密码<span class="text-danger">*</span></label>
            <input type="password" class="form-control" name="password" id="password" placeholder="请输入密码">
        </div>
        <div class="form-group">
            <label>确认密码</label>
            <input type="password" class="form-control" name="confirmPwd" placeholder="请再次输入密码">
        </div>
        <div class="form-group">
            <label>手机<span class="text-danger">*</span></label>
            <input type="number" class="form-control" name="mobile" placeholder="请输入手机号码" value="${param.mobile}">
            <p class="text-danger">${error_mobile}</p>
        </div>
        <div class="form-group">
            <label>电子邮件</label>
            <input type="email" class="form-control" name="email" placeholder="请输入电子邮件" value="${param.email}">
            <p class="text-danger">${error_email}</p>
        </div>
        <div class="form-group">
            <label>头像</label>
            <div>
                <label class="radio-inline">
                    <input type="radio" name="photo" value="1.jpg" checked><img src="img/photos/1.jpg" class="img-circle" width="60px">
                </label>
                <label class="radio-inline">
                    <input type="radio" name="photo" value="2.jpg"><img src="img/photos/2.jpg" class="img-circle" width="60px">
                </label>
                <label class="radio-inline">
                    <input type="radio" name="photo" value="3.jpg"><img src="img/photos/3.jpg" class="img-circle" width="60px">
                </label>
                <label class="radio-inline">
                    <input type="radio" name="photo" value="4.jpg"><img src="img/photos/4.jpg" class="img-circle" width="60px">
                </label>
            </div>
        </div>
        <div class="form-group">
            <button class="btn btn-primary btn-block" type="submit">注册</button>
        </div>
        <div class="form-group">
            注册本系统即代表您同意<a href="#">服务条款</a>
        </div>
    </form>
</div>
<%@include file="commons/footer.jsp"%>
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap-datepicker.min.js"></script>
<script src="js/bootstrap-datepicker.zh-CN.min.js"></script>
<script src="js/jquery.validate.min.js"></script>
<script src="js/messages_zh.js"></script>

<script>
    $().ready(function (){
        $('#signupForm').validate({
            rules: {
                account: 'required',
                nickname: 'required',
                password: {
                    required: true,
                    minlength: 6
                },
                confirmPwd: {
                    required: true,
                    equalTo: '#password'
                },
                mobile: {
                    required: true,
                    isPhone: true
                },
                email: 'email',
                gender:{
                    isGender:true
                }
            },
            messages: {
                confirmPwd: {
                    equalTo: '两次密码输入不一至！'
                }
            },
            errorElement: "em",
            errorPlacement: function ( error, element ) {
                // 给错误元素增加`text-danger` class 样式
                error.addClass( "text-danger" );

                // Add `has-feedback` class to the parent div.form-group
                // in order to add icons to inputs
                element.parents( ".col-sm-5" ).addClass( "has-feedback" );

                if ( element.prop( "type" ) === "checkbox" ) {
                    error.insertAfter( element.parent( "label" ) );
                } else {
                    error.insertAfter( element );
                }

                // Add the span element, if doesn't exists, and apply the icon classes to it.
                if ( !element.next( "span" )[ 0 ] ) {
                    $( "<span class='glyphicon glyphicon-remove form-control-feedback'></span>" ).insertAfter( element );
                }
            }
        });

    });

    // 增加自定义手机号码验证
    $.validator.addMethod("isPhone", function(value,element) {
        var length = value.length;
        var mobile = /^1[3456789]\d{9}$/;
        return this.optional(element) || (length == 11 && mobile.test(value));
    }, "请正确填写您的联系电话");

    // 增加自定义性别验证
    $.validator.addMethod("isGender", function(value,element) {
        var length = value.length;
        var mobile = /^[男女]$/;
        return this.optional(element) || ( mobile.test(value));
    }, "请正确填写您的性别");

</script>
</body>
</html>
