package cdp.fms.sevlet;

import cdp.fms.dao.Impl.UserDaoImpl;
import cdp.fms.dao.UserDao;
import cdp.fms.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String account = request.getParameter("account");
        String password = request.getParameter("password");

        UserDao userDao = new UserDaoImpl();

        User user = userDao.loginUser(account, password);
        if (user != null) { //登陆成功
            request.getSession().setAttribute("user", user);

//            String rootPath = request.getServletPath(); //获取项目根目录的绝对路径
//            response.sendRedirect(rootPath + "/main.jsp");
            request.setAttribute("page", "main.jsp");
            request.setAttribute("message", "账户登录成功，欢迎使用本财务管理系统。");
            request.getRequestDispatcher("alert_jump.jsp").forward(request, response);
        } else { //登陆失败
            request.setAttribute("error", "账户或密码错误！");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }
}
