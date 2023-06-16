package cdp.fms.sevlet;

import cdp.fms.dao.Impl.UserDaoImpl;
import cdp.fms.dao.UserDao;
import cdp.fms.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;

@WebServlet(name = "ChangeUserInfoServlet", value = "/ChangeUserInfoServlet")
public class ChangeUserInfoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");  // 防止中文文件名乱码

        String account = request.getParameter("account");
        String nickname = request.getParameter("username");
        String gender = request.getParameter("gender");
        String email = request.getParameter("email");
        String mobile = request.getParameter("phone");

        User user = new User();

        user.setAccount(account);
        user.setName(nickname);
        user.setEmail(email);
        user.setPhone(mobile);
        user.setGender(gender);

        // 获取头像
        Part part = request.getPart("photo");
        if (part != null && part.getSize() > 0) {
            String uploadFilePath = this.getServletContext().getRealPath("/img/photos");
            String fileName = part.getSubmittedFileName();
            // 写文件
            part.write(uploadFilePath + File.separator + fileName);
            user.setPhoto(fileName);
        } else {
            String photo = ((User) request.getSession().getAttribute("user")).getPhoto(); //从session中获得当前值
            user.setPhoto(photo);
        }

        //保存用户信息至数据库
        UserDao userService = new UserDaoImpl();
        userService.changeUser(user);
        //更新当前会话中用的户信息对象
        request.getSession().setAttribute("user", user);


        // 页面跳转
        request.setAttribute("page", "main.jsp");
        request.setAttribute("message", "个人信息修改成功。");
        request.getRequestDispatcher("alert_jump.jsp").forward(request, response);
    }

}
