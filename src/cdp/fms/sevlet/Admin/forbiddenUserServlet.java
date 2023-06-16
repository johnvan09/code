package cdp.fms.sevlet.Admin;

import cdp.fms.dao.CategoryDao;
import cdp.fms.dao.Impl.CategoryDaoImpl;
import cdp.fms.dao.Impl.UserDaoImpl;
import cdp.fms.dao.UserDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "forbiddenUserServlet", value = "/Admin/forbiddenUserServlet")
public class forbiddenUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int uid = Integer.parseInt(request.getParameter("uid"));

        // 根据 id 获取要删除的 Book 对象

        UserDao billBookDao = new UserDaoImpl();

        if (billBookDao.searchUserById(uid) != null) { // 如果获取到了要删除的 Book 对象
            billBookDao.forbiddenUser(uid);
            // 重定向到 book 列表页面
            response.sendRedirect(request.getContextPath() + "user-list.jsp");
        } else { // 如果没有获取到要删除的 Book 对象
            // 返回错误信息
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
