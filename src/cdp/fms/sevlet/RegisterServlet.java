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
import java.util.Random;

@WebServlet(name = "RegisterServlet", value = "/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         /*
          获取参数
         */
        request.setCharacterEncoding("UTF-8"); //设置编码，解决Post方式的中文乱码问题
        String account = request.getParameter("account");
        String nickname = request.getParameter("nickname");
        String password = request.getParameter("password");
        String gender = request.getParameter("gender");
        String mobile = request.getParameter("mobile");
        String email = request.getParameter("email");
        String photo = request.getParameter("photo");
        // 验证

        /*
          数据验证
         */
        UserDao userDao = new UserDaoImpl();
        String error_account = null;
        if (userDao.isUserExisted("account", account)) {
            error_account = "账户已经存在！";
            request.setAttribute("error_account", error_account);
        }
        String error_nickname = null;
        if (userDao.isUserExisted("name", nickname)) {
            error_nickname = "昵称已经存在！";
            request.setAttribute("error_username", error_nickname);
        }
        String error_mobile = null;
        if (userDao.isUserExisted("phone", mobile)) {
            error_mobile = "手机号码已经存在！";
            request.setAttribute("error_phone_number", error_mobile);
        }
        String error_email = null;
        if (email != null && userDao.isUserExisted("email", email)) { // 注意：email为null时
            error_email = "电子邮件已经存在！";
            request.setAttribute("error_email", error_email);
        }


        /*
          根据验证结果进行页面跳转
         */
        if (error_account == null && error_nickname == null && error_mobile == null && error_email == null) { //验证通过
            User user = new User();
            user.setAccount(account);
            user.setPsd(password);
            user.setName(nickname);
            user.setGender(gender);
            user.setPhoto(photo);
            user.setPhone(mobile);
            user.setEmail(email);


            Random r = new Random();
            while (true){
                int id = r.nextInt(100000000);
                if (!userDao.isUserExisted("userId", String.valueOf(id))) {
                    user.setId(id);
                    break;
                }
            }


            // 写库
            if (userDao.addUser(user)) {
                request.setAttribute("page", "index.jsp");
                request.setAttribute("message", "账户注册成功，请使用该账户登陆系统。");
                request.getRequestDispatcher("alert_jump.jsp").forward(request, response);
            }
        } else { // 验证失败-返回注册页面
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }

}
