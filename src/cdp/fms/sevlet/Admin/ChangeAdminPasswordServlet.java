package cdp.fms.sevlet.Admin;

import cdp.fms.dao.AdminDao;
import cdp.fms.dao.Impl.AdminDaoImpl;
import cdp.fms.dao.Impl.UserDaoImpl;
import cdp.fms.dao.UserDao;
import cdp.fms.model.Admin;
import cdp.fms.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ChangeAdminPasswordServlet", value = "/Admin/ChangeAdminPasswordServlet")
public class ChangeAdminPasswordServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Admin admin = (Admin) request.getSession().getAttribute("admin");
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");

        admin.setPsd(newPassword);
        AdminDao userService = new AdminDaoImpl();

        // 先验证旧密码是否正确
        if(userService.login(admin.getAccount(), oldPassword) != null) { //旧密码正确
            userService.update(admin);

            request.setAttribute("page", "../admin/admin-list.jsp");
            request.setAttribute("message", "密码修改成功，下次请使用新密码登陆。");
            request.getRequestDispatcher("../alert_jump.jsp").forward(request, response);
        } else { // 旧密码不正确
            request.setAttribute("error", "旧密码不正确！");
            request.getRequestDispatcher("../admin/change_admin_psd.jsp").forward(request, response);
        }
    }
}
