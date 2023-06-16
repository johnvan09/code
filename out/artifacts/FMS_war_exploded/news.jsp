<%@ page import="cdp.fms.dao.NewsDao" %>
<%@ page import="cdp.fms.dao.Impl.NewsDaoImpl" %>
<%@ page import="cdp.fms.model.News" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2023/6/10
  Time: 10:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>新闻详情</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/quill.snow.css">
    <link rel="stylesheet" href="css/quill-emoji.css">
    <link rel="stylesheet" href="css/main.css">
</head>
<body>
<jsp:include page="commons/header.jsp"></jsp:include>
<%
    int id = Integer.parseInt(request.getParameter("id"));
    System.out.println(id);
    NewsDao newsService = new NewsDaoImpl();
    News news = newsService.getNewsById(id);

    List<News> recommendedNews = newsService.getHotNews();

    // 更新点击计数
    newsService.updateLiuLanNums(id);
%>
<div class="container">
    <div class="col-xs-8">

        <h2 class="news-title"><%=news.getName()%>
        </h2>
        <div class="news-status">
            <%=news.getNums()%>浏览量
        </div>
        <div class="news-content">
            <%=news.getNinfo()%>
        </div>
        <div class="col-xs-4">
            <div class="side-bar-card">
                <div class="card-title">相关推荐</div>
                <div class="card-body">
                    <div class="list">
                        <% for (News n : recommendedNews) {%>
                        <div class="item clearfix">
                            <div class="col-xs-5 no-padding-h">
                                <img src="img/<%=n.getNimg()%>" alt="#">
                            </div>
                            <div class="col-xs-7">
                                <div class="title"><a href="news.jsp?id=<%=n.getNid()%>"><%=n.getName()%>
                                </div>
                                <div class="desc"><%=n.getNums()%>浏览量
                                </div>
                            </div>
                            <%}%>
                        </div>
                    </div>
                </div>
                <jsp:include page="commons/hot-news.jsp"></jsp:include>
            </div>
        </div>
        <%@include file="commons/footer.jsp" %>
        <script src="js/jquery.min.js"></script>
        <script src="js/jquery.cookie.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/quill.min.js"></script>
        <script src="js/quill-emoji.js"></script>
</body>
</html>
