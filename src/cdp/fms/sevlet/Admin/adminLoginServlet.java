package cdp.fms.sevlet.Admin;

import cdp.fms.dao.AdminDao;
import cdp.fms.dao.Impl.AdminDaoImpl;
import cdp.fms.model.Admin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "adminLoginServlet", value = "/Admin/adminLoginServlet")
public class adminLoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String account = request.getParameter("account");
        String password = request.getParameter("password");

        AdminDao userDao = new AdminDaoImpl();

        Admin admin = userDao.login(account, password);
        if (admin != null) { //登陆成功
            request.getSession().setAttribute("admin", admin);

            String rootPath = request.getServletPath(); //获取项目根目录的绝对路径

            System.out.println(rootPath);
            request.setAttribute("page", "../admin/admin-list.jsp");
            request.setAttribute("message", "账户登录成功，欢迎使用本财务管理系统。");
            request.getRequestDispatcher("../alert_jump.jsp").forward(request,response);
        } else { //登陆失败
            request.setAttribute("error", "账户或密码错误！");
            request.getRequestDispatcher("../admin/login.jsp").forward(request, response);
        }
    }
}
