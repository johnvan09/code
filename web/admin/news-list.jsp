<%@ page import="java.util.List" %>
<%@ page import="cdp.fms.model.Admin" %>
<%@ page import="cdp.fms.model.News" %>
<%@ page import="cdp.fms.dao.NewsDao" %>
<%@ page import="cdp.fms.dao.Impl.NewsDaoImpl" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2023/6/12
  Time: 21:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>财务管理系统</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
</head>
<body>
<%@include file="commons/menu.jsp" %>
<div class="container">
    <div class="row">
        <div class="col-sm-2">
            <%--                添加账单--%>
            <div class="list-group side-bar hidden-xs">
                <a href="${pageContext.request.contextPath}/admin/addNew.jsp" class="list-group-item">添加新闻</a>
            </div>
        </div>
        <div class="col-sm-7">
            <%
                Admin auth = (Admin) request.getSession().getAttribute("admin");
                List<News> newsList = null;
                if (auth != null) {
                    request.setAttribute("admin", auth);
                    NewsDao adminDao = new NewsDaoImpl();
                    newsList = adminDao.getAllNews();
                } else {
                    response.sendRedirect("../admin/login.jsp");
                }
            %>
            <table class="table table-light">
                <thead>
                <tr>
                    <th scope="col">新闻名</th>
                    <th scope="col">新闻图片</th>
                    <th scope="col">新闻作者</th>
                    <th scope="col">新闻点击数</th>
                    <th scope="col">新闻发布时间</th>
                </tr>
                </thead>
                <tbody>

                <%
                    if (newsList != null) {
                        for (News o : newsList) {
                %>
                <tr>
                    <td><%=o.getName()%>
                    </td>
                    <td><%=o.getNimg()%>
                    </td>
                    <td><%=o.getWriter()%>
                    </td>
                    <td><%=o.getNums()%>
                    </td>
                    <td><%=o.getNcdate()%>
                    </td>
                    <td><a class="btn btn-sm btn-danger" href="admin/change_new.jsp?bid=<%=o.getNid()%>">修改</a>
                        <form action="${pageContext.request.contextPath}/Admin/deleteNewServlet" method="post">
                            <input type="hidden" name="Bid" value="<%=o.getNid()%>"/>
                            <input type="submit" value="删除"/>
                        </form>
                    </td>

                </tr>
                <%
                        }
                    }
                %>
                </tbody>

            </table>
        </div>

    </div>
    <%@include file="commons/footer.jsp" %>
    <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</body>
</html>
