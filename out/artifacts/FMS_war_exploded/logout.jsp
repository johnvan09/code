<%--
  Created by IntelliJ IDEA.
  User: zhao
  Date: 2021/12/29
  Time: 11:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  session.removeAttribute("user"); // 在session中删除user对象
  response.sendRedirect("main.jsp"); // 重定向至首页
%>>
