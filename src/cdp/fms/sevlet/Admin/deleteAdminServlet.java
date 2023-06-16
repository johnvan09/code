package cdp.fms.sevlet.Admin;

import cdp.fms.dao.AdminDao;
import cdp.fms.dao.Impl.AdminDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "deleteAdminServlet", value = "/Admin/deleteAdminServlet")
public class deleteAdminServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int wid = Integer.parseInt(request.getParameter("Bid"));

        // 根据 id 获取要删除的 Book 对象

        AdminDao billBookDao = new AdminDaoImpl();

        if (billBookDao.getAdminById(wid) != null) { // 如果获取到了要删除的 Book 对象
            billBookDao.delete(wid);
            // 重定向到 book 列表页面
            response.sendRedirect(request.getContextPath() + "admin-list.jsp");
        } else { // 如果没有获取到要删除的 Book 对象
            // 返回错误信息
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
