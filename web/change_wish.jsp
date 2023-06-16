<%@ page import="cdp.fms.dao.CategoryDao" %>
<%@ page import="cdp.fms.dao.Impl.CategoryDaoImpl" %>
<%@ page import="cdp.fms.model.Category" %>
<%@ page import="java.util.List" %>
<%@ page import="cdp.fms.dao.BillBookDao" %>
<%@ page import="cdp.fms.dao.Impl.BillBookDaoImpl" %>
<%@ page import="cdp.fms.model.BillBook" %>
<%@ page import="cdp.fms.dao.WishListDao" %>
<%@ page import="cdp.fms.dao.Impl.WishListDaoImpl" %>
<%@ page import="cdp.fms.model.Wishlist" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>心愿信息</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/main.css">
</head>
<body>
<%@include file="commons/header.jsp" %>
<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1。
    response.setHeader("Pragma", "no-cache"); // HTTP 1.0。
    response.setDateHeader("Expires", 0); // Proxies。
    int wid = Integer.parseInt(request.getParameter("wid"));
    WishListDao billBookDao = new WishListDaoImpl();
    Wishlist wishlist = billBookDao.getWishlistById(wid);
%>
<div class="container">
    <div class="container">
        <div class="panel panel-info">
            <div class="panel-heading">
                <h3 class="panel-title">
                    修改账单信息
                </h3>
            </div>
            <div class="panel-body" style="padding: 20px;">
                <form action="ChangeBillbookServlet" class="form-horizontal" role="form" method="post">
                    <div class="form-group">
                        <label for="bid" class="col-sm-2 control-label">心愿编号：</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" readonly name="wid" id="bid"
                                    value="<%=wishlist.getWid()%>">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="bname" class="col-sm-2 control-label">心愿名：</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="wname" id="bname"
                                   placeholder="请输入账单名" value="<%=wishlist.getWname()%>">
                        </div>
                    </div>



                    <div class="form-group">
                        <label for="money" class="col-sm-2 control-label">总金额：</label>
                        <div class="col-sm-3">
                            <input type="number" min="0" class="form-control" name="waoney" id="money"
                                   placeholder="请输入总金额"
                                   value="<%=wishlist.getwAllMoney()%>">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="hmoney" class="col-sm-2 control-label">总金额：</label>
                        <div class="col-sm-3">
                            <input type="number" min="0" class="form-control" name="whoney" id="hmoney"
                                   placeholder="请输入已完成金额"
                                   value="<%=wishlist.getWhaveMoney()%>">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="way" class="col-sm-2 control-label">完成状态：</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" readonly name="status" id="way" placeholder="请输入收支方式"
                                   value="<%=wishlist.getWstatus()%>">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="beizhu" class="col-sm-2 control-label">创建时间：</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="wcdate" id="beizhu" placeholder="请输入备注"
                                   value="<%=wishlist.getWcdate()%>">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="bcdate" class="col-sm-2 control-label">完成时间:</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="wfdate" id="bcdate"
                                   placeholder="请选择您的收支时间" value="<%=wishlist.getWfdate()%>">
                        </div>

                    </div>

                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <button type="submit" class="btn btn-default">确定</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<%@include file="commons/footer.jsp" %>
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/bootstrap-datepicker.min.js"></script>
<script src="js/bootstrap-datepicker.zh-CN.min.js"></script>
</body>
</html>
