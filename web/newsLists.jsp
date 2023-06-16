<%@ page import="cdp.fms.model.News" %>
<%@ page import="cdp.fms.dao.NewsDao" %>
<%@ page import="java.util.List" %>
<%@ page import="cdp.fms.dao.Impl.NewsDaoImpl" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2023/6/13
  Time: 16:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>财务新闻</title>
    <link rel="stylesheet" href="./css/bootstrap.min.css">
</head>
<body>
<%@include file="commons/header.jsp" %>
<div align="center" >
    <img src="img/background.jpg" height="400" width="1080" alt=".." style="margin-bottom: 10px;">
</div>
<div>
    <div class="col-sm-2">

    </div>
    <div class="col-sm-7">
        <div class="news-list">
            <%
                NewsDao newsDao = new NewsDaoImpl();
                List<News> newsList;
                newsList = newsDao.getAllNews();
                pageContext.setAttribute("newsList", newsList);

            %>
            <c:forEach items="${newsList}" var="news">
                <div class="news-list-item clearfix">
                    <div class="col-xs-5">
                        <img src="img/${news.nimg}" width="340" height="80">
                    </div>
                    <div class="col-xs-7">
                        <a href="news.jsp?id=${news.nid}" class="title">${news.name}</a>
                        <div class="info">
                            <span class="avatar"><img src="img/logo.jpg" width="100" height="50"></span>
                            <span>${news.writer}</span>•
                            <span>${news.nums}浏览量</span>•
                            <span>${news.ncdate}</span>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
    <div class="col-sm-3">
    </div>

</div>
<script src="js/bootstrap-datepicker.min.js"></script>
<script src="js/bootstrap-datepicker.zh-CN.min.js"></script>
</body>
</html>
