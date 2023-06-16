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
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@WebServlet(name = "changeWishServlet", value = "/changeWishServlet")
public class changeWishServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8"); //设置编码，解决Post方式的中文乱码问题
        String wname = request.getParameter("wish");
        int nums = Integer.parseInt(request.getParameter("wnum"));
        String wcdate = request.getParameter("wdate");
        int havenums = Integer.parseInt(request.getParameter("whnums"));

        Wishlist wishlist = new Wishlist();
        WishListDao wishListDao = new WishListDaoImpl();
        wishlist.setWname(wname);
        wishlist.setwAllMoney(nums);
        wishlist.setWhaveMoney(havenums);
        wishlist.setWcdate(Date.valueOf(wcdate));
        if(wishListDao.changeWishlist(wishlist)){
            request.setAttribute("page", "wishlist.jsp");
            request.setAttribute("message", "修改心愿成功");
        }
    }
}
