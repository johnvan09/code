<%@ page import="cdp.fms.model.User" %>
<%@ page import="cdp.fms.model.Wishlist" %>
<%@ page import="java.util.List" %>
<%@ page import="cdp.fms.dao.WishListDao" %>
<%@ page import="cdp.fms.dao.Impl.WishListDaoImpl" %>
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
                <a href="addwish.jsp" class="list-group-item">添加心愿</a>
            </div>
        </div>
        <div class="col-sm-7">
            <%
                User auth = (User) request.getSession().getAttribute("user");
                List<Wishlist> billBookList = null;
                if (auth != null) {
                    request.setAttribute("user", auth);
                    WishListDao wishListDao = new WishListDaoImpl();
                    billBookList = wishListDao.getWishlistByUserId(auth.getId());
                } else {
                    response.sendRedirect("index.jsp");
                }
            %>
            <table class="table table-light">
                <thead>
                <tr>
                    <th scope="col">心愿名</th>
                    <th scope="col">心愿总金额</th>
                    <th scope="col">心愿已完成金额</th>
                    <th scope="col">心愿完成状态</th>
                    <th scope="col">心愿创建时间</th>
                    <th scope="col">操做</th>
                </tr>
                </thead>
                <tbody>

                <%
                    if (billBookList != null) {
                        for (Wishlist o : billBookList) {%>
                <tr>
                    <td><%=o.getWname()%>
                    </td>
                    <td>￥<%=o.getwAllMoney()%>
                    </td>
                    <td>￥<%=o.getWhaveMoney()%>
                    </td>
                    <td><%=o.getWstatus()%>
                    </td>
                    <td><%=o.getWcdate()%>
                    </td>
                    <td><a class="btn btn-sm btn-danger" href="change_wish.jsp?wid=<%=o.getWid()%>">修改</a>
                        <form action="deleteWishServlet" method="post">
                            <input type="hidden" name="Wid" value="<%=o.getWid()%>"/>
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