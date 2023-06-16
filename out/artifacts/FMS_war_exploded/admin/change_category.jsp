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
    <title>分类信息</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
</head>
<body>
<%@include file="commons/menu.jsp" %>
<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1。
    response.setHeader("Pragma", "no-cache"); // HTTP 1.0。
    response.setDateHeader("Expires", 0); // Proxies。
    int wid = Integer.parseInt(request.getParameter("wid"));
    CategoryDao billBookDao = new CategoryDaoImpl();
    Category wishlist = billBookDao.getCategoryById(wid);
%>
<div class="container">
    <div class="container">
        <div class="panel panel-info">
            <div class="panel-heading">
                <h3 class="panel-title">
                    修改分类信息
                </h3>
            </div>
            <div class="panel-body" style="padding: 20px;">
                <form action="${pageContext.request.contextPath}/Admin/ChangeCategoryServlet" class="form-horizontal" role="form" method="post">
                    <div class="form-group">
                        <label for="bid" class="col-sm-2 control-label">分类编号：</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" readonly name="wid" id="bid"
                                   value="<%=wishlist.getId()%>">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="inorout" class="col-sm-2 control-label">收入或者支出：</label>
                        <select name="inorout" id="inorout">
                            <option value="收入">收入</option>
                            <option value="支出">支出</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <label>具体分类名<span class="text-danger">*</span></label>
                        <input type="text" class="form-control" name="category"  value="<%=wishlist.getCategoryName()%>">
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
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap-datepicker.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap-datepicker.zh-CN.min.js"></script>
</body>
</html>

