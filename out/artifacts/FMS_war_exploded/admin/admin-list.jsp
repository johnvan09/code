
<%@ page import="java.util.List" %>
<%@ page import="cdp.fms.model.Admin" %>
<%@ page import="cdp.fms.dao.AdminDao" %>
<%@ page import="cdp.fms.dao.Impl.AdminDaoImpl" %><%--
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
                <a href="${pageContext.request.contextPath}/admin/addAdmin.jsp" class="list-group-item">添加管理员</a>
            </div>
        </div>
        <div class="col-sm-7">
            <%
                Admin auth = (Admin) request.getSession().getAttribute("admin");
                List<Admin> adminList = null;
                if (auth != null) {
                    request.setAttribute("admin", auth);
                    AdminDao adminDao = new AdminDaoImpl();
                    adminList = adminDao.getAllAdmins();
                } else {
                    response.sendRedirect("admin/login.jsp");
                }
            %>
            <table class="table table-light">
                <thead>
                <tr>
                    <th scope="col">管理员编号</th>
                    <th scope="col">管理员账户</th>
                </tr>
                </thead>
                <tbody>

                <%
                    if (adminList != null) {
                        for (Admin o : adminList) {
                %>
                <tr>
                    <td><%=o.getId()%>
                    </td>
                    <td><%=o.getAccount()%>
                    </td>
                    <td><a class="btn btn-sm btn-danger" href="admin/change_admin_psd.jsp?bid=<%=o.getId()%>">修改密码</a>
                        <form action="${pageContext.request.contextPath}/Admin/deleteAdminServlet" method="post">
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
