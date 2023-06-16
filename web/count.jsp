<%@ page import="cdp.fms.model.User" %>
<%@ page import="cdp.fms.model.BillBook" %>
<%@ page import="java.util.List" %>
<%@ page import="cdp.fms.dao.BillBookDao" %>
<%@ page import="cdp.fms.dao.Impl.BillBookDaoImpl" %>
<%@ page import="java.time.LocalDate" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--财务统计--%>

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

        </div>
        <div class="col-sm-7">
            <%
                User auth = (User) request.getSession().getAttribute("user");
                List<BillBook> billBookList1 = null;
                List<BillBook> billBookList2 = null;
                List<BillBook> billBookList3 = null;
                List<BillBook> billBookList4 = null;
                LocalDate today = LocalDate.now();
                int month = today.getMonthValue();
                int year = today.getYear();

                if (auth != null) {

                    request.setAttribute("user", auth);
                    BillBookDao billBookDao = new BillBookDaoImpl();
                    billBookList1 = billBookDao.getMonthIncome(month, auth.getId());
                    billBookList2 = billBookDao.getMonthOutcome(month, auth.getId());
                    billBookList3 = billBookDao.getYearIncome(year, auth.getId());
                    billBookList4 = billBookDao.getYearOutcome(year, auth.getId());


                } else {
                    response.sendRedirect("main.jsp");
                }
            %>
            <h3>本月财务统计</h3>
            <table class="table table-light">
                <thead>
                <tr>
                    <th scope="col">本月支出金额</th>
                    <th scope="col">本月收入金额</th>
                    <th scope="col">本年支出金额</th>
                    <th scope="col">本年收入金额</th>
                </tr>
                </thead>
                <tbody>

                <%
                    if (billBookList1 != null && billBookList2 != null && billBookList3 != null && billBookList4 != null) {
                        float a = 0, b = 0, c = 0, d = 0;
                        for (BillBook o : billBookList1) {
                            a = o.getMoney() + a;
                            System.out.println(a);
                        }
                        for (BillBook o1 : billBookList2) {
                            b = o1.getMoney() + b;
                        }
                        for (BillBook o2 : billBookList4) {
                            c = o2.getMoney() + c;
                        }
                        for (BillBook o3 : billBookList3) {
                            d = o3.getMoney() + d;
                        }


                %>
                <tr>
                    <td>￥<%=b%>
                    </td>
                    <td>￥<%=a%>
                    </td>
                    <td>￥<%=c%>
                    </td>
                    <td>￥<%=d%>
                    </td>

                </tr>
                <%

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