package cdp.fms.sevlet;

import cdp.fms.dao.BillBookDao;
import cdp.fms.dao.BudgetDao;
import cdp.fms.dao.CategoryDao;
import cdp.fms.dao.Impl.BillBookDaoImpl;
import cdp.fms.dao.Impl.BudgetDaoImpl;
import cdp.fms.dao.Impl.CategoryDaoImpl;
import cdp.fms.model.BillBook;
import cdp.fms.model.Budget;
import cdp.fms.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Date;

@WebServlet(name = "changeBudgetServlet", value = "/changeBudgetServlet")
public class changeBudgetServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");  // 防止中文文件名乱码
        User user = (User) request.getSession().getAttribute("user");
        int bid = Integer.parseInt(request.getParameter("bid"));
        String bname = request.getParameter("bname");
        String money = request.getParameter("money");
        Date bcdate = Date.valueOf(request.getParameter("bcdate"));
        String info = request.getParameter("info");
        int category = Integer.parseInt(request.getParameter("categoryName"));

        Budget billBook = new Budget();

        billBook.setBuid(bid);
        billBook.setBuname(bname);
        billBook.setBmoney(Float.parseFloat(money));
        billBook.setBtime(bcdate);
        billBook.setBinfo(info);

        CategoryDao categoryDao = new CategoryDaoImpl();

        if (categoryDao.isUserExisted("id", String.valueOf(category))) {
            billBook.setCategory(categoryDao.getCategoryById(category));
        }
        billBook.setUser(user);

        //保存用户信息至数据库
        BudgetDao billBookDao = new BudgetDaoImpl();
        if (billBookDao.changeBudget(billBook))
        {
            // 页面跳转
            request.setAttribute("page", "budget.jsp");
            request.setAttribute("message", "预算信息修改成功。");
            request.getRequestDispatcher("alert_jump.jsp").forward(request, response);
        }
    }
}
