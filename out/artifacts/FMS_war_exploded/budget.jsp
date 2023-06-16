<%@ page import="cdp.fms.model.User" %>
<%@ page import="cdp.fms.model.BillBook" %>
<%@ page import="cdp.fms.dao.BillBookDao" %>
<%@ page import="cdp.fms.dao.Impl.BillBookDaoImpl" %>
<%@ page import="java.util.List" %>
<%@ page import="cdp.fms.model.Budget" %>
<%@ page import="cdp.fms.dao.BudgetDao" %>
<%@ page import="cdp.fms.dao.Impl.BudgetDaoImpl" %><%--
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
    <link rel="stylesheet" href="./css/bootstrap.min.css">
    <link rel="stylesheet" href="./css/main.css">
</head>
<body>
<%@include file="commons/header.jsp" %>
<div class="container">
    <div class="row">
        <div class="col-sm-2">
            <%--                添加账单--%>
            <div class="list-group side-bar hidden-xs">
                <a href="addBudget.jsp" class="list-group-item">添加预算</a>
            </div>
        </div>
        <div class="col-sm-7">
            <%
                User auth = (User) request.getSession().getAttribute("user");
                List<Budget> budgetList = null;
                if (auth != null) {
                    request.setAttribute("user", auth);
                    BudgetDao billBookDao = new BudgetDaoImpl();
                    budgetList = billBookDao.getBudget(auth.getId());
                } else {
                    response.sendRedirect("index.jsp");
                }
            %>
            <table class="table table-light">
                <thead>
                <tr>
                    <th scope="col">预算名</th>
                    <th scope="col">分类</th>
                    <th scope="col">金额</th>
                    <th scope="col">日期</th>
                    <th scope="col">操做</th>
                </tr>
                </thead>
                <tbody>

                <%
                    if (budgetList != null) {
                        for (Budget o : budgetList) {
                %>
                <tr>
                    <td><%=o.getBuname()%>
                    </td>
                    <td><%=o.getCategory().getCategoryName()%>
                    </td>
                    <td><%=o.getBmoney()%>
                    </td>
                    <td><%=o.getBtime()%>
                    </td>
                    <td><a class="btn btn-sm btn-danger" href="change_billBooks.jsp?buid=<%=o.getBuid()%>">修改</a>
                        <form action="deleteBillBookServlet" method="post">
                            <input type="hidden" name="buid" value="<%=o.getBuid()%>" />
                            <input type="submit" value="删除" />
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
        <div class="col-sm-3">
            <div class="search-bar">
                <input type="search" class="form-control" placeholder="搜一下">
            </div>
            <div class="side-bar-card flag clearfix">
                <div class="col-xs-5">
                    <img src="img/logo.jpg">
                </div>
                <div class="col-xs-7">
                    <div class="text-lg">联系我们</div>
                    <div>电话：12377</div>
                </div>
            </div>
            <jsp:include page="commons/hot-news.jsp"></jsp:include>
        </div>
    </div>
</div>
<%@include file="commons/footer.jsp" %>
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
</body>
</html>
