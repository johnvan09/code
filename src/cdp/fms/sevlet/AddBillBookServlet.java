package cdp.fms.sevlet;

import cdp.fms.dao.BillBookDao;
import cdp.fms.dao.CategoryDao;
import cdp.fms.dao.Impl.BillBookDaoImpl;
import cdp.fms.dao.Impl.CategoryDaoImpl;
import cdp.fms.model.BillBook;
import cdp.fms.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@WebServlet(name = "AddBillBookServlet", value = "/AddBillBookServlet")
public class AddBillBookServlet extends HttpServlet {
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
        User user = (User) request.getSession().getAttribute("user");
        String bname = request.getParameter("bname");
        if(request.getParameter("bname")==null){
            System.out.println("bname is error");
        }
        String inorout = request.getParameter("inorout");
        int categoryName = Integer.parseInt(request.getParameter("categoryName"));
        float money = Float.parseFloat(request.getParameter("money"));
        if(request.getParameter("money")==null){
            System.out.println("money is error");
        }
        String way = request.getParameter("way");
        String beizhu = request.getParameter("beizhu");
        Date bcdate = Date.valueOf(request.getParameter("bcdate"));


        BillBook billBook = new BillBook();
        BillBookDao billBookDao = new BillBookDaoImpl();
        CategoryDao categoryDao = new CategoryDaoImpl();

        billBook.setBname(bname);
        billBook.setMoney(money);
        billBook.setWay(way);
        billBook.setBeizhu(beizhu);

        billBook.setBcdate(bcdate);

        CategoryDao categoryDao1 = new CategoryDaoImpl();

        if (categoryDao1.isUserExisted("id", String.valueOf(categoryName))) {
            billBook.setCategory(categoryDao.getCategoryById(categoryName));
        }
        billBook.setUser(user);
        /*
          页面跳转
         */
        // 写库
        if (billBookDao.addBillBook(billBook)) {
            request.setAttribute("page", "main.jsp");
            request.setAttribute("message", "添加账单成功");
            request.getRequestDispatcher("alert_jump.jsp").forward(request, response);
        } else { // 返回添加页面
            request.getRequestDispatcher("addBillBook.jsp").forward(request, response);
        }
    }
}
