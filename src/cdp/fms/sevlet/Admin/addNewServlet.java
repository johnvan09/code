package cdp.fms.sevlet.Admin;

import cdp.fms.dao.BillBookDao;
import cdp.fms.dao.CategoryDao;
import cdp.fms.dao.Impl.BillBookDaoImpl;
import cdp.fms.dao.Impl.CategoryDaoImpl;
import cdp.fms.dao.Impl.NewsDaoImpl;
import cdp.fms.dao.NewsDao;
import cdp.fms.model.BillBook;
import cdp.fms.model.News;
import cdp.fms.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;
import java.sql.Date;

@WebServlet(name = "addNewServlet", value = "/Admin/addNewServlet")
public class addNewServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/*
          获取参数
         */
        request.setCharacterEncoding("UTF-8"); //设置编码，解决Post方式的中文乱码问题
        String bname = request.getParameter("bname");
        String writer = request.getParameter("writer");
        String info = request.getParameter("way");
        Date bcdate = Date.valueOf(request.getParameter("bcdate"));

        News news =new News();
        news.setName(bname);
        news.setNinfo(info);
        news.setNums(0);
        news.setWriter(writer);
        news.setNcdate(bcdate);
        // 获取头像
        Part part = request.getPart("photo");
        if (part != null && part.getSize() > 0) {
            String uploadFilePath = this.getServletContext().getRealPath("/img/photos");
            String fileName = part.getSubmittedFileName();
            // 写文件
            part.write(uploadFilePath + File.separator + fileName);
            news.setNimg(fileName);
        } else {
            String photo = ((User) request.getSession().getAttribute("user")).getPhoto(); //从session中获得当前值
            news.setNimg(photo);
        }


        NewsDao newsDao = new NewsDaoImpl();


        /*
          页面跳转
         */
        // 写库
        if (newsDao.addNews(news)) {
            request.setAttribute("page", "../admin/news-list.jsp");
            request.setAttribute("message", "添加新闻成功");
            request.getRequestDispatcher("../alert_jump.jsp").forward(request, response);
        } else { // 返回添加页面
            request.getRequestDispatcher("../addNew.jsp").forward(request, response);
        }
    }
}
