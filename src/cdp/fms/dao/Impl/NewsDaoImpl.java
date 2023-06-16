package cdp.fms.dao.Impl;

import cdp.fms.dao.NewsDao;
import cdp.fms.model.News;
import cdp.fms.uitl.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NewsDaoImpl implements NewsDao {
    @Override
    public boolean addNews(News news) {
        try {
            //获取连接
            Connection con = DBUtil.getConnection();

            //执行查询并返回结果集
            PreparedStatement pst = con.prepareStatement("insert into news(nname, ninfo, nimg, writer, nlnums, ncdate" +
                    " ) values(?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, news.getName());
            pst.setString(2, news.getNinfo());
            pst.setString(3, news.getNimg());
            pst.setString(4, news.getWriter());
            pst.setInt(5, 0);
            pst.setDate(6, (Date) news.getNcdate());

            int result = pst.executeUpdate();

            //释放连接资源
            pst.close();
            con.close();

            return result > 0; //大于0表示成功创建一行
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean changeNews(News news) {
        try {
            //获取连接
            Connection con = DBUtil.getConnection();

            //执行查询并返回结果集
            PreparedStatement pst = con.prepareStatement("update news set nname=?, ninfo=?, nimg=?, writer=?, " +
                    "ncdate=? where nid=?");
            pst.setString(1, news.getName());
            pst.setString(2, news.getNinfo());
            pst.setString(3, news.getNimg());
            pst.setString(4, news.getWriter());
            pst.setDate(5, (Date) news.getNcdate());
            pst.setInt(6, news.getNid());

            int result = pst.executeUpdate();

            //释放连接资源
            pst.close();
            con.close();

            return result > 0; //大于0表示成功创建一行
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteNews(int newsId) {
        try {
            //获取连接
            Connection con = DBUtil.getConnection();

            PreparedStatement pst = con.prepareStatement("delete from news where nid=?");
            pst.setInt(1, newsId);

            pst.executeUpdate();

            //释放连接资源
            pst.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public News getNewsById(int newsId) {
        try {
            //获取连接
            Connection con = DBUtil.getConnection();

            //执行查询并返回结果集
            PreparedStatement pst = con.prepareStatement("select * from news where nid=?");
            pst.setInt(1, newsId);

            ResultSet rs = pst.executeQuery();
            News news = new News();
            if (rs.next()) {
                news.setName(rs.getString("nname"));
                news.setNid(rs.getInt("nid"));
                news.setNinfo(rs.getString("ninfo"));
                news.setNimg(rs.getString("nimg"));
                news.setNums(rs.getInt("nlnums"));
                news.setWriter(rs.getString("writer"));
                news.setNcdate(rs.getDate("ncdate"));

            }

            //释放连接资源
            rs.close();
            pst.close();
            con.close();

            //返回结果
            return news;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<News> getHotNews() {
        try {
            //获取连接
            Connection con = DBUtil.getConnection();

            //执行查询并返回结果集
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from news order by ncdate desc limit 5 ");
            //封装结果为列表数据
            List<News> newsList = new ArrayList<>();
            while (rs.next()) {
                News news = new News();

                news.setNid(rs.getInt("nid"));
                news.setName(rs.getString("nname"));
                news.setNimg(rs.getString("nimg"));
                news.setNinfo(rs.getString("ninfo"));
                news.setNcdate(rs.getDate("ncdate"));
                news.setWriter(rs.getString("writer"));
                news.setNums(rs.getInt("nlnums"));

                newsList.add(news);
            }

            //释放连接资源
            rs.close();
            st.close();
            con.close();

            //返回结果
            return newsList;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<News> getAllNews() {
        try {
            //获取连接
            Connection con = DBUtil.getConnection();

            //执行查询并返回结果集
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from news");
            //封装结果为列表数据
            List<News> newsList = new ArrayList<>();
            while (rs.next()) {
                News news = new News();

                news.setNid(rs.getInt("nid"));
                news.setName(rs.getString("nname"));
                news.setNimg(rs.getString("nimg"));
                news.setNinfo(rs.getString("ninfo"));
                news.setNcdate(rs.getDate("ncdate"));
                news.setWriter(rs.getString("writer"));
                news.setNums(rs.getInt("nlnums"));

                newsList.add(news);
            }

            //释放连接资源
            rs.close();
            st.close();
            con.close();

            //返回结果
            return newsList;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void updateLiuLanNums(int newsId) {
        try {
            //获取连接
            Connection con = DBUtil.getConnection();
            PreparedStatement pst = con.prepareStatement("update news set nlnums=news.nlnums+1 where nid=?");
            pst.setInt(1, newsId);

            pst.executeUpdate();

            //释放连接资源
            pst.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
