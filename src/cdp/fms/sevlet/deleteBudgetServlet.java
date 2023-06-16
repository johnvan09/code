package cdp.fms.sevlet;

import cdp.fms.dao.BillBookDao;
import cdp.fms.dao.BudgetDao;
import cdp.fms.dao.Impl.BillBookDaoImpl;
import cdp.fms.dao.Impl.BudgetDaoImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "deleteBudgetServlet", value = "/deleteBudgetServlet")
public class deleteBudgetServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int buid = Integer.parseInt(request.getParameter("buid"));

        // 根据 id 获取要删除的 Book 对象

        BudgetDao billBookDao = new BudgetDaoImpl();

        if (billBookDao.deleteBudget(buid) ) { // 如果获取到了要删除的 Book 对象

            // 重定向到 book 列表页面
            response.sendRedirect(request.getContextPath() + "budget.jsp");
        } else { // 如果没有获取到要删除的 Book 对象
            // 返回错误信息
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
