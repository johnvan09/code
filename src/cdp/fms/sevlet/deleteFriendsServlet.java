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

@WebServlet(name = "deleteFriendsServlet", value = "/deleteFriendsServlet")
public class deleteFriendsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");  // 防止中文文件名乱码
        User user = (User) request.getSession().getAttribute("user");
        int id = Integer.parseInt(request.getParameter("id"));

        // 根据 id 获取要删除的 对象

        UserDao billBookDao = new UserDaoImpl();

        if (billBookDao.overFriend(user.getId(), id)) { // 如果获取到了要删除的对象

            // 重定向到 book 列表页面
            response.sendRedirect(request.getContextPath() + "friends.jsp");
        } else { // 如果没有获取到要删除的对象
            // 返回错误信息
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
