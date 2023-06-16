package cdp.fms.sevlet.Admin;

import cdp.fms.dao.CategoryDao;
import cdp.fms.dao.Impl.CategoryDaoImpl;
import cdp.fms.dao.Impl.NewsDaoImpl;
import cdp.fms.dao.NewsDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "deleteNewServlet", value = "/Admin/deleteNewServlet")
public class deleteNewServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int wid = Integer.parseInt(request.getParameter("Bid"));

        // 根据 id 获取要删除的 Book 对象

        NewsDao billBookDao = new NewsDaoImpl();

        if (billBookDao.getNewsById(wid) != null) { // 如果获取到了要删除的 Book 对象
            billBookDao.deleteNews(wid);
            // 重定向到 book 列表页面
            response.sendRedirect(request.getContextPath() + "news-list.jsp");
        } else { // 如果没有获取到要删除的 Book 对象
            // 返回错误信息
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
