<%@ page import="cdp.fms.dao.NewsDao" %>
<%@ page import="cdp.fms.dao.Impl.NewsDaoImpl" %>
<%@ page import="cdp.fms.model.News" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    NewsDao newsDao = new NewsDaoImpl();
    List<News> hotNews = newsDao.getHotNews();
    pageContext.setAttribute("hotNews", hotNews);
%>
<div class="side-bar-card">
    <div class="card-title">热门财经新闻</div>
    <div class="card-body">
        <div class="list">
            <c:forEach items="${hotNews}" var="news">
                <div class="item">
                    <a class="title" href="news.jsp?id=${news.nid}">${news.name}</a>
                    <div class="desc">${news.nums}阅读</div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
