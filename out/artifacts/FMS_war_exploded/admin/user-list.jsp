<%@ page import="java.util.List" %>
<%@ page import="cdp.fms.model.Admin" %>
<%@ page import="cdp.fms.dao.AdminDao" %>
<%@ page import="cdp.fms.dao.Impl.AdminDaoImpl" %>
<%@ page import="cdp.fms.model.User" %>
<%@ page import="cdp.fms.dao.UserDao" %>
<%@ page import="cdp.fms.dao.Impl.UserDaoImpl" %><%--
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
        <div class="col-sm-7">
            <%
                Admin auth = (Admin) request.getSession().getAttribute("admin");
                List<User> adminList = null;
                if (auth != null) {
                    request.setAttribute("admin", auth);
                    UserDao adminDao = new UserDaoImpl();
                    adminList = adminDao.getAllUsers();
                } else {
                    response.sendRedirect("admin/login.jsp");
                }
            %>
            <table class="table table-light">
                <thead>
                <tr>
                    <th scope="col">用户编号</th>
                    <th scope="col">用户账户</th>
                    <th scope="col">用户姓名</th>
                    <th scope="col">用户性别</th>
                    <th scope="col">用户电话</th>
                    <th scope="col">用户电邮</th>
                    <th scope="col">是否禁用</th>
                </tr>
                </thead>
                <tbody>

                <%
                    if (adminList != null) {
                        for (User o : adminList) {
                %>
                <tr>
                    <td><%=o.getId()%>
                    </td>
                    <td><%=o.getAccount()%>
                    </td>
                    <td><%=o.getName()%>
                    </td>
                    <td><%=o.getGender()%>
                    </td>
                    <td><%=o.getPhone()%>
                    </td>
                    <td><%=o.getEmail()%>
                    </td>
                    <td>
                        <%
                            if (o.getForbidden() == 1) {

                        %>是
                        <%
                        } else {%>
                        否
                        <%
                            }
                        %>

                    </td>


                    <td>
                        <form action="${pageContext.request.contextPath}/Admin/forbiddenUserServlet" method="post">
                            <input type="hidden" name="uid" value="<%=o.getId()%>"/>
                            <input type="submit" value="禁用"/>
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
