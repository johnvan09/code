package cdp.fms.sevlet;

import cdp.fms.dao.BillBookDao;
import cdp.fms.dao.CategoryDao;
import cdp.fms.dao.Impl.BillBookDaoImpl;
import cdp.fms.dao.Impl.CategoryDaoImpl;
import cdp.fms.dao.Impl.WishListDaoImpl;
import cdp.fms.dao.WishListDao;
import cdp.fms.model.BillBook;
import cdp.fms.model.User;
import cdp.fms.model.Wishlist;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Date;

@WebServlet(name = "chanegWishServlet", value = "/chanegWishServlet")
public class chanegWishServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");  // 防止中文文件名乱码
        User user = (User) request.getSession().getAttribute("user");
        int wid = Integer.parseInt(request.getParameter("wid"));
        String wname = request.getParameter("wname");
        String wamoney = request.getParameter("wamoney");
        String whmoney = request.getParameter("whmoney");
        String status = request.getParameter("status");
        Date wcdate = Date.valueOf(request.getParameter("wcdate"));
        Date wfdate = Date.valueOf(request.getParameter("wfdate"));


        Wishlist billBook = new Wishlist();

        billBook.setWid(wid);
        billBook.setWname(wname);
        billBook.setwAllMoney(Float.parseFloat(wamoney));
        billBook.setWhaveMoney(Float.parseFloat(whmoney));
        billBook.setWstatus(status);
        billBook.setWcdate(wcdate);
        billBook.setWfdate(wfdate);


        //保存用户信息至数据库
        WishListDao billBookDao = new WishListDaoImpl();
        if (billBookDao.changeWishlist(billBook))
        {
            // 页面跳转
            request.setAttribute("page", "wishlist.jsp");
            request.setAttribute("message", "心愿信息修改成功。");
            request.getRequestDispatcher("alert_jump.jsp").forward(request, response);
        }
    }
}
