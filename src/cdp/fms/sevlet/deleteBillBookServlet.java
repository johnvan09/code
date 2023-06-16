package cdp.fms.sevlet;

import cdp.fms.dao.BillBookDao;
import cdp.fms.dao.Impl.BillBookDaoImpl;
import cdp.fms.dao.Impl.WishListDaoImpl;
import cdp.fms.dao.WishListDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "deleteBillBookServlet", value = "/deleteBillBookServlet")
public class deleteBillBookServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int wid = Integer.parseInt(request.getParameter("Bid"));

        // 根据 id 获取要删除的 Book 对象

        BillBookDao billBookDao = new BillBookDaoImpl();

        if (billBookDao.deleteBillBookById(wid) ) { // 如果获取到了要删除的 Book 对象

            // 重定向到 book 列表页面
            response.sendRedirect(request.getContextPath() + "main.jsp");
        } else { // 如果没有获取到要删除的 Book 对象
            // 返回错误信息
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
