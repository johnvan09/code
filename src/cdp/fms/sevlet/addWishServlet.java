package cdp.fms.sevlet;

import cdp.fms.dao.Impl.WishListDaoImpl;
import cdp.fms.dao.WishListDao;
import cdp.fms.model.User;
import cdp.fms.model.Wishlist;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Random;

@WebServlet(name = "addWishServlet", value = "/addWishServlet")
public class addWishServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8"); //设置编码，解决Post方式的中文乱码问题
        User user = (User) request.getSession().getAttribute("user");
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
            if(wishListDao.addWish(wishlist,user.getId())){
                request.setAttribute("page", "wishlist.jsp");
                request.setAttribute("message", "添加心愿成功");
                request.getRequestDispatcher("alert_jump.jsp").forward(request, response);
            }
            else {
                request.setAttribute("page", "addwish.jsp");
            }
        }

    }
}
