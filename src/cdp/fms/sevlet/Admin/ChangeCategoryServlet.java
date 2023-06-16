package cdp.fms.sevlet.Admin;

import cdp.fms.dao.CategoryDao;
import cdp.fms.dao.Impl.CategoryDaoImpl;
import cdp.fms.model.Category;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ChangeCategoryServlet", value = "/Admin/ChangeCategoryServlet")
public class ChangeCategoryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8"); //设置编码，解决Post方式的中文乱码问题
        String inorout = request.getParameter("inorout");
        String category= request.getParameter("category");
        Category category1 = new Category();
        category1.setCategoryName(category);
        category1.setInorout(inorout);
        CategoryDao c = new CategoryDaoImpl();
        if (c.changeCategory(category1)){
            request.setAttribute("page", "../admin/category-list.jsp");
            request.setAttribute("message", "分类修改成功。");
            request.getRequestDispatcher("../alert_jump.jsp").forward(request, response);
        }

    }
}
