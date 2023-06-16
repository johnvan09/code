<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Cache-Control" content="no-cache">
<meta http-equiv="Expires" content="0">

<div class="navbar navbar-default">
    <div class="container">

        <%--        <!-- class="visible-xs-inline-block"：在超小屏幕上显示-->--%>
        <label for="toggle-checkbox" id="toggle-label" class="visible-xs-inline-block">财务管理系统</label>
        <input type="checkbox" class="hidden" id="toggle-checkbox">
        <div class="hidden-xs">
            <ul class="nav navbar-nav">
                <li>
                    <img src="img/logo.jpg" height="50" width="150">
                </li>
                <li id="manage_m"><a href="main.jsp">财务管理</a></li>
                <li id="count_m"><a href="count.jsp">财务统计</a></li>
                <li id="analyst"><a href="analyst.jsp">财务分析</a></li>
                <li id="budget"><a href="budget.jsp">财务预算</a></li>
                <li id="wishlist"><a href="wishlist.jsp">心愿单</a></li>
                <li id="news"><a href="newsLists.jsp">财务新闻</a></li>
                <li id="friends"><a href="friends.jsp">我的好友</a> </li>

            </ul>

            <ul class="nav navbar-nav navbar-right">
                <c:if test="${not empty sessionScope.user}">
                    <li><a href="#"><img src="${pageContext.request.contextPath}/img/photos/${sessionScope.user.photo}"
                                         class="img-circle" width="32px"></a></li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                           aria-expanded="false">${sessionScope.user.name}<span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="userinfo.jsp">个人信息</a></li>
                            <li><a href="change_password.jsp">修改密码</a></li>
                            <li role="separator" class="divider"></li>
                            <li><a href="javascript:logout()">退出</a></li>
                        </ul>
                    </li>


                </c:if>
                <c:if test="${empty sessionScope.user}">
                    <li><a href="index.jsp">登陆</a></li>
                </c:if>
                <li><a href="register.jsp">注册</a></li>
            </ul>
        </div>
    </div>
</div>
<script>
    function logout() {
        if (confirm("您确定定要退出吗？")) {
            window.location.href = "logout.jsp"; //前端页面跳转
        }
    }
</script>
<script>
    // 获取导航栏项
    let navItem = document.getElementById('manage_m');
    let navItem1 = document.getElementById('count_m');
    let navItem2 = document.getElementById('analyst');
    let navItem3 = document.getElementById('budget');
    let navItem4 = document.getElementById('wishlist');
    let navItem5 = document.getElementById('news');


    // 添加事件监听器
    navItem.addEventListener('click', function () {
        // 获取当前选中的导航栏项
        let current = document.querySelector('.active');

        // 如果当前选中的不是该导航栏项，则设置该项为选中状态
        if (current !== navItem) {
            current.classList.remove('active');
            navItem.classList.add('active');
        }
    });
    navItem1.addEventListener('click', function () {
        // 获取当前选中的导航栏项
        let current = document.querySelector('.active');

        // 如果当前选中的不是该导航栏项，则设置该项为选中状态
        if (current !== navItem1) {
            current.classList.remove('active');
            navItem1.classList.add('active');
        }
    });
    navItem2.addEventListener('click', function () {
        // 获取当前选中的导航栏项
        let current = document.querySelector('.active');

        // 如果当前选中的不是该导航栏项，则设置该项为选中状态
        if (current !== navItem2) {
            current.classList.remove('active');
            navItem2.classList.add('active');
        }
    });

    navItem3.addEventListener('click', function () {
        // 获取当前选中的导航栏项
        let current = document.querySelector('.active');

        // 如果当前选中的不是该导航栏项，则设置该项为选中状态
        if (current !== navItem3) {
            current.classList.remove('active');
            navItem3.classList.add('active');
        }
    });
    navItem4.addEventListener('click', function () {
        // 获取当前选中的导航栏项
        let current = document.querySelector('.active');

        // 如果当前选中的不是该导航栏项，则设置该项为选中状态
        if (current !== navItem4) {
            current.classList.remove('active');
            navItem4.classList.add('active');
        }
    });
    navItem5.addEventListener('click', function () {
        // 获取当前选中的导航栏项
        let current = document.querySelector('.active');

        // 如果当前选中的不是该导航栏项，则设置该项为选中状态
        if (current !== navItem5) {
            current.classList.remove('active');
            navItem5.classList.add('active');
        }
    });


</script>
