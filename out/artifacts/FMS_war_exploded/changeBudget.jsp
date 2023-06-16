<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2023/6/14
  Time: 23:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="cdp.fms.dao.CategoryDao" %>
<%@ page import="cdp.fms.dao.Impl.CategoryDaoImpl" %>
<%@ page import="cdp.fms.model.Category" %>
<%@ page import="java.util.List" %>
<%@ page import="cdp.fms.dao.BillBookDao" %>
<%@ page import="cdp.fms.dao.Impl.BillBookDaoImpl" %>
<%@ page import="cdp.fms.model.BillBook" %>
<%@ page import="cdp.fms.dao.BudgetDao" %>
<%@ page import="cdp.fms.dao.Impl.BudgetDaoImpl" %>
<%@ page import="cdp.fms.model.Budget" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>预算信息</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/main.css">
</head>
<body>
<%@include file="commons/header.jsp" %>
<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1。
    response.setHeader("Pragma", "no-cache"); // HTTP 1.0。
    response.setDateHeader("Expires", 0); // Proxies。
    int buid = Integer.parseInt(request.getParameter("buid"));
    BudgetDao billBookDao = new BudgetDaoImpl();
    Budget billBook = billBookDao.getBudgetById(buid);
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
                        <label for="bid" class="col-sm-2 control-label">预算编号：</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" readonly name="bid" id="bid"
                                   placeholder="请输入账单名" value="<%=billBook.getBuid()%>">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="bname" class="col-sm-2 control-label">预算名：</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="bname" id="bname"
                                   placeholder="请输入账单名" value="<%=billBook.getBuname()%>">
                        </div>
                    </div>


                    <div class="form-group">
                        <label for="categoryName" class="col-sm-2 control-label">具体分类:</label>
                        <div class="col-sm-4">
                            <select name="categoryName" id="categoryName" class="form-control">
                                <%
                                    CategoryDao categoryDao = new CategoryDaoImpl();
                                    List<Category> categories = categoryDao.getAllCategories();
                                    for (Category c : categories) {
                                %>
                                <option value="<%=c.getId()%>" ${param.categoryName == c.getId() ? 'selected' : ''}
                                ><%=c.getCategoryName()%>
                                </option>
                                <%
                                    }
                                %>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="money" class="col-sm-2 control-label">金额：</label>
                        <div class="col-sm-3">
                            <input type="number" min="0" class="form-control" name="money" id="money"
                                   placeholder="请输入金额"
                                   value="<%=billBook.getBmoney()%>">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="beizhu" class="col-sm-2 control-label">信息：</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="info" id="beizhu" placeholder="请输入备注"
                                   value="<%=billBook.getBinfo()%>">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="bcdate" class="col-sm-2 control-label">创建时间:</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="bcdate" id="bcdate"
                                   placeholder="请选择您的收支时间" value="<%=billBook.getBtime()%>">
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

