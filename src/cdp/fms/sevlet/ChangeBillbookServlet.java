package cdp.fms.sevlet;

import cdp.fms.dao.BillBookDao;
import cdp.fms.dao.CategoryDao;
import cdp.fms.dao.Impl.BillBookDaoImpl;
import cdp.fms.dao.Impl.CategoryDaoImpl;
import cdp.fms.dao.Impl.UserDaoImpl;
import cdp.fms.dao.UserDao;
import cdp.fms.model.BillBook;
import cdp.fms.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@WebServlet(name = "ChangeBillbookServlet", value = "/ChangeBillbookServlet")
public class ChangeBillbookServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");  // 防止中文文件名乱码
        User user = (User) request.getSession().getAttribute("user");
        int bid = Integer.parseInt(request.getParameter("bid"));
        String bname = request.getParameter("bname");
        String money = request.getParameter("money");
        String way = request.getParameter("way");
        Date bcdate = Date.valueOf(request.getParameter("bcdate"));
        String beizhu = request.getParameter("beizhu");
        int category = Integer.parseInt(request.getParameter("categoryName"));

        BillBook billBook = new BillBook();

        billBook.setBid(bid);
        billBook.setBname(bname);
        billBook.setWay(way);
        billBook.setBeizhu(beizhu);
        billBook.setMoney(Float.parseFloat(money));
        billBook.setBcdate(bcdate);

        CategoryDao categoryDao = new CategoryDaoImpl();

        if (categoryDao.isUserExisted("id", String.valueOf(category))) {
            billBook.setCategory(categoryDao.getCategoryById(category));
        }
        billBook.setUser(user);

        //保存用户信息至数据库
        BillBookDao billBookDao = new BillBookDaoImpl();
        if (billBookDao.changeBillBook(billBook))
        {
            // 页面跳转
            request.setAttribute("page", "main.jsp");
            request.setAttribute("message", "账单信息修改成功。");
            request.getRequestDispatcher("alert_jump.jsp").forward(request, response);
        }



    }
}
