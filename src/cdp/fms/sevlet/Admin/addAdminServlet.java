package cdp.fms.sevlet.Admin;

import cdp.fms.dao.AdminDao;
import cdp.fms.dao.Impl.AdminDaoImpl;
import cdp.fms.model.Admin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "addAdminServlet", value = "/Admin/addAdminServlet")
public class addAdminServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String account = request.getParameter("account");
        String psd = request.getParameter("password");

        Admin admin = new Admin();
        admin.setAccount(account);
        admin.setPsd(psd);
        AdminDao adminDao =new AdminDaoImpl();
        if (adminDao.add(admin)){
            request.setAttribute("page", "../admin/admin-list.jsp");
            request.setAttribute("message", "新增管理员成功。");
            request.getRequestDispatcher("../alert_jump.jsp").forward(request, response);
        }
    }
}
