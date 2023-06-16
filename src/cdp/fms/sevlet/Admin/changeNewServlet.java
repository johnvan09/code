package cdp.fms.sevlet.Admin;

import cdp.fms.dao.Impl.NewsDaoImpl;
import cdp.fms.dao.Impl.UserDaoImpl;
import cdp.fms.dao.NewsDao;
import cdp.fms.dao.UserDao;
import cdp.fms.model.News;
import cdp.fms.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;

@WebServlet(name = "changeNewServlet", value = "/Admin/changeNewServlet")
public class changeNewServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");  // 防止中文文件名乱码

        String nname = request.getParameter("nname");
        String writer = request.getParameter("writer");
        String info = request.getParameter("info");

        News user = new News();

        user.setName(nname);
        user.setWriter(writer);
        user.setNinfo(info);
        // 获取头像
        Part part = request.getPart("photo");
        if (part != null && part.getSize() > 0) {
            String uploadFilePath = this.getServletContext().getRealPath("/img/photos");
            String fileName = part.getSubmittedFileName();
            // 写文件
            part.write(uploadFilePath + File.separator + fileName);
            user.setNimg(fileName);
        } else {
            String photo = ((User) request.getSession().getAttribute("user")).getPhoto(); //从session中获得当前值
            user.setNimg(photo);
        }

        //保存用户信息至数据库
        NewsDao userService = new NewsDaoImpl();
        userService.changeNews(user);

        // 页面跳转
        request.setAttribute("page", "../news-list.jsp");
        request.setAttribute("message", "新闻信息修改成功。");
        request.getRequestDispatcher("../alert_jump.jsp").forward(request, response);

    }
}
