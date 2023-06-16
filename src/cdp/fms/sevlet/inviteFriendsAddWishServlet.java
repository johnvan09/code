package cdp.fms.sevlet;

import cdp.fms.dao.Impl.WishListDaoImpl;
import cdp.fms.dao.WishListDao;
import cdp.fms.model.User;
import cdp.fms.model.Wishlist;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Date;
import java.util.Random;

@WebServlet(name = "inviteFriendsAddWishServlet", value = "/inviteFriendsAddWishServlet")
public class inviteFriendsAddWishServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8"); //设置编码，解决Post方式的中文乱码问题
        User user = (User) request.getSession().getAttribute("user");
        int friend = Integer.parseInt(request.getParameter("friendid"));
        String wname = request.getParameter("wname");
        float money = Float.parseFloat(request.getParameter("allmoney"));
        String wcdate = request.getParameter("wcdate");


        Wishlist wishlist = new Wishlist();
        WishListDao wishListDao = new WishListDaoImpl();
        if (wname != null && wcdate != null) {
            Random r = new Random();
            while (true) {
                int id = r.nextInt(100000000);
                if (!wishListDao.isWishExisted("wid", String.valueOf(id))) {
                    wishlist.setWid(id);
                    break;
                }
            }
            wishlist.setWname(wname);
            wishlist.setwAllMoney(money);
            wishlist.setWhaveMoney(0);
            wishlist.setWstatus("未完成");
            wishlist.setWcdate(Date.valueOf(wcdate));
            if(wishListDao.inviteFriendsWishlist(wishlist,user.getId(),friend)){
                request.setAttribute("page", "friends.jsp");
                request.setAttribute("message", "添加心愿成功");
                request.getRequestDispatcher("alert_jump.jsp").forward(request, response);
            }
            else {
                request.setAttribute("page", "inviteToAddWish.jsp");
            }
        }
    }
}
