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

@WebServlet(name = "addBudgetServlet", value = "/addBudgetServlet")
public class addBudgetServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8"); //设置编码，解决Post方式的中文乱码问题
        User user = (User) request.getSession().getAttribute("user");
        String bname = request.getParameter("bname");
        int categoryName = Integer.parseInt(request.getParameter("categoryName"));
        float money = Float.parseFloat(request.getParameter("money"));
        String info = request.getParameter("info");
        Date bcdate = Date.valueOf(request.getParameter("bcdate"));


        Budget billBook = new Budget();
        BudgetDao billBookDao = new BudgetDaoImpl();
        CategoryDao categoryDao = new CategoryDaoImpl();

        billBook.setBuname(bname);
        billBook.setBmoney(money);
        billBook.setBinfo(info);

        billBook.setBtime(bcdate);

        CategoryDao categoryDao1 = new CategoryDaoImpl();

        if (categoryDao1.isUserExisted("id", String.valueOf(categoryName))) {
            billBook.setCategory(categoryDao.getCategoryById(categoryName));
        }
        billBook.setUser(user);
        /*
          页面跳转
         */
        // 写库
        if (billBookDao.addBudget(billBook)) {
            request.setAttribute("page", "budget.jsp");
            request.setAttribute("message", "添加预算成功");
            request.getRequestDispatcher("alert_jump.jsp").forward(request, response);
        } else { // 返回添加页面
            request.getRequestDispatcher("addBudget.jsp").forward(request, response);
        }
    }
}
