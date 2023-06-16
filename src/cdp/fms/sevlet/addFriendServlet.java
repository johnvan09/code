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

@WebServlet(name = "addFriendServlet", value = "/addFriendServlet")
public class addFriendServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");  // 防止中文文件名乱码
        User user = (User) request.getSession().getAttribute("user");
        String account = request.getParameter("account");

        boolean success = false;
        // 在这里处理添加好友的逻辑，如果添加成功，将 success 设置为 true
        User friends;
        UserDao userDao = new UserDaoImpl();
        friends = userDao.getUserByAccount(account);
        if (friends != null) {
            userDao.makeFriends(user.getId(), friends.getId());
        }
        request.setAttribute("success", success);
        request.getRequestDispatcher("addFriends.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
