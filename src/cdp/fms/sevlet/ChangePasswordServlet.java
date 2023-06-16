package cdp.fms.sevlet;

import cdp.fms.dao.Impl.UserDaoImpl;
import cdp.fms.dao.UserDao;
import cdp.fms.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ChangePasswordServlet", value = "/ChangePasswordServlet")
public class ChangePasswordServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User)request.getSession().getAttribute("user");
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");

        UserDao userService = new UserDaoImpl();

        // 先验证旧密码是否正确
        if(userService.loginUser(user.getAccount(), oldPassword) != null) { //旧密码正确
            userService.changePsd(user.getAccount(), newPassword);

            request.setAttribute("page", "index.jsp");
            request.setAttribute("message", "密码修改成功，下次请使用新密码登陆。");
            request.getRequestDispatcher("alert_jump.jsp").forward(request, response);
        } else { // 旧密码不正确
            request.setAttribute("error", "旧密码不正确！");
            request.getRequestDispatcher("change_password.jsp").forward(request, response);
        }
    }
}
