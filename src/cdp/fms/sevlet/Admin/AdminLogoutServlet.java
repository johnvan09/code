package cdp.fms.sevlet.Admin;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AdminLogoutServlet", value = "/Admin/AdminLogoutServlet")
public class AdminLogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().removeAttribute("admin"); // 删除传话中的admin对象

        Cookie adminLoginCookie =new Cookie("adminLogin", null); // 创建要删除的对象
        adminLoginCookie.setMaxAge(0); //立即删除

        response.addCookie(adminLoginCookie);  // 重新写入，将覆盖之前的值

        response.sendRedirect( request.getContextPath() + "../admin/login.jsp"); // 重定向至登陆页
    }
}
