<%@ page import="cdp.fms.model.User" %>
<%@ page import="cdp.fms.model.BillBook" %>
<%@ page import="cdp.fms.dao.BillBookDao" %>
<%@ page import="cdp.fms.dao.Impl.BillBookDaoImpl" %>
<%@ page import="java.util.List" %><%--
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
                <a href="addBillBook.jsp" class="list-group-item">添加账单</a>
            </div>
        </div>
        <div class="col-sm-7">
            <%
                User auth = (User) request.getSession().getAttribute("user");
                List<BillBook> billBookList = null;
                if (auth != null) {
                    request.setAttribute("user", auth);
                    BillBookDao billBookDao = new BillBookDaoImpl();
                    billBookList = billBookDao.getBillBookList(auth.getId());
                } else {
                    response.sendRedirect("index.jsp");
                }
            %>
            <table class="table table-light">
                <thead>
                <tr>
                    <th scope="col">账单名</th>
                    <th scope="col">收入或者支出</th>
                    <th scope="col">分类</th>
                    <th scope="col">金额</th>
                    <th scope="col">支付方式</th>
                    <th scope="col">日期</th>
                    <th scope="col">操做</th>
                </tr>
                </thead>
                <tbody>

                <%
                    if (billBookList != null) {
                        for (BillBook o : billBookList) {
                %>
                <tr>
                    <td><%=o.getBname()%>
                    </td>
                    <td><%=o.getCategory().getInorout()%>
                    </td>
                    <td><%=o.getCategory().getCategoryName()%>
                    </td>
                    <td><%=o.getMoney()%>
                    </td>
                    <td><%=o.getWay()%>
                    </td>
                    <td><%=o.getBcdate()%>
                    </td>
                    <td><a class="btn btn-sm btn-danger" href="change_billBooks.jsp?bid=<%=o.getBid()%>">修改</a>
                        <form action="deleteBillBookServlet" method="post">
                            <input type="hidden" name="Bid" value="<%=o.getBid()%>" />
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
