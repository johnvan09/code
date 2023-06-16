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
                    <img src="${pageContext.request.contextPath}/img/logo.jpg" height="50" width="150">
                </li>
                <li id="manage_m"><a href="${pageContext.request.contextPath}/admin/admin-list.jsp">管理员管理</a></li>
                <li id="count_m"><a href="${pageContext.request.contextPath}/admin/user-list.jsp">用户管理</a></li>
                <li id="analyst"><a href="${pageContext.request.contextPath}/admin/news-list.jsp">新闻管理</a></li>
                <li id="budget"><a href="${pageContext.request.contextPath}/admin/category-list.jsp">分类管理</a></li>

            </ul>

        </div>
    </div>
</div>
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
