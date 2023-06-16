package cdp.fms.sevlet.Admin;

import cdp.fms.dao.AdminDao;
import cdp.fms.dao.CategoryDao;
import cdp.fms.dao.Impl.AdminDaoImpl;
import cdp.fms.dao.Impl.CategoryDaoImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "deleteCategoryServlet", value = "/Admin/deleteCategoryServlet")
public class deleteCategoryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int wid = Integer.parseInt(request.getParameter("Bid"));

        // 根据 id 获取要删除的 Book 对象

        CategoryDao billBookDao = new CategoryDaoImpl();

        if (billBookDao.getCategoryById(wid) != null) { // 如果获取到了要删除的 Book 对象
            billBookDao.deleteCategoryById(wid);
            // 重定向到 book 列表页面
            response.sendRedirect(request.getContextPath() + "category-list.jsp");
        } else { // 如果没有获取到要删除的 Book 对象
            // 返回错误信息
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
