
<%@ page import="java.util.List" %>
<%@ page import="cdp.fms.model.Admin" %>
<%@ page import="cdp.fms.dao.AdminDao" %>
<%@ page import="cdp.fms.dao.Impl.AdminDaoImpl" %>
<%@ page import="cdp.fms.model.Category" %>
<%@ page import="cdp.fms.dao.CategoryDao" %>
<%@ page import="cdp.fms.dao.Impl.CategoryDaoImpl" %><%--
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
                <a href="${pageContext.request.contextPath}/admin/addCategory.jsp" class="list-group-item">添加分类</a>
            </div>
        </div>
        <div class="col-sm-7">
            <%
                Admin auth = (Admin) request.getSession().getAttribute("admin");
                List<Category> categoryList = null;
                if (auth != null) {
                    request.setAttribute("admin", auth);
                    CategoryDao categoryDao = new CategoryDaoImpl();
                    categoryList = categoryDao.getAllCategories();
                } else {
                    response.sendRedirect("admin/login.jsp");
                }
            %>
            <table class="table table-light">
                <thead>
                <tr>
                    <th scope="col">分类编号</th>
                    <th scope="col">收入或者支出</th>
                    <th scope="col">具体分类名</th>
                </tr>
                </thead>
                <tbody>

                <%
                    if (categoryList != null) {
                        for (Category o : categoryList) {
                %>
                <tr>
                    <td><%=o.getId()%>
                    </td>
                    <td><%=o.getInorout()%>
                    </td>
                    <td><%=o.getCategoryName()%>
                    </td>
                    <td><a class="btn btn-sm btn-danger" href="admin/change_category.jsp?bid=<%=o.getId()%>">修改</a>
                        <form action="${pageContext.request.contextPath}/Admin/deleteCategoryServlet" method="post">
                            <input type="hidden" name="Bid" value="<%=o.getId()%>"/>
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
