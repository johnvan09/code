package cdp.fms.dao.Impl;

import cdp.fms.dao.BillBookDao;
import cdp.fms.dao.CategoryDao;
import cdp.fms.dao.UserDao;
import cdp.fms.model.BillBook;
import cdp.fms.model.Category;
import cdp.fms.model.User;
import cdp.fms.uitl.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BillBookDaoImpl implements BillBookDao {

    @Override
    public boolean addBillBook(BillBook billBook) {
        try {
            Connection con = DBUtil.getConnection();

            PreparedStatement pst = con.prepareStatement("insert into money(mname, mnums, mway, mtime, id, userId," +
                    " beizhu ) values(?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, billBook.getBname());
            pst.setFloat(2, billBook.getMoney());
            pst.setString(3, billBook.getWay());
            pst.setDate(4, (Date) billBook.getBcdate());
            pst.setInt(5, billBook.getCategory().getId());
            pst.setInt(6, billBook.getUser().getId());
            pst.setString(7, billBook.getBeizhu());

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
    public boolean changeBillBook(BillBook billBook) {
        try {
            Connection con = DBUtil.getConnection();
            PreparedStatement pst = con.prepareStatement("update money set mname=?,mway=?,mnums=?,mtime=?,id=? ,beizhu=?" +
                    "where mid=?");

            pst.setString(1, billBook.getBname());
            pst.setString(2, billBook.getWay());
            pst.setFloat(3, billBook.getMoney());
            pst.setDate(4, (Date) billBook.getBcdate());
            pst.setInt(5, billBook.getCategory().getId());
            pst.setString(6, billBook.getBeizhu());
            pst.setInt(7, billBook.getBid());

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
    public boolean deleteBillBookById(int bid) {
        try {
            //获取连接
            Connection con = DBUtil.getConnection();

            PreparedStatement pst = con.prepareStatement("delete from money where mid=?");
            pst.setInt(1, bid);

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
    public BillBook getBillBookById(int bid) {
        try {
            //获取连接
            Connection con = DBUtil.getConnection();

            //执行查询并返回结果集
            PreparedStatement pst = con.prepareStatement("select * from money where mid=?");
            pst.setInt(1, bid);
            ResultSet rs = pst.executeQuery();
            BillBook billBook = null;
            if (rs.next()) {
                billBook = new BillBook();
                billBook.setBid(rs.getInt("mid"));
                billBook.setBname(rs.getString("mname"));
                billBook.setMoney(rs.getFloat("mnums"));
                billBook.setWay(rs.getString("mway"));
                billBook.setBcdate(rs.getDate("mtime"));

                CategoryDao categoryDao = new CategoryDaoImpl();
                Category category = categoryDao.getCategoryById(rs.getInt("id"));
                billBook.setCategory(category);

                UserDao userDao = new UserDaoImpl();
                User user = userDao.searchUserById(rs.getInt("userId"));
                billBook.setUser(user);
            }

            //释放连接资源
            rs.close();
            pst.close();
            con.close();

            //返回结果
            return billBook;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<BillBook> getBillBookList(int userId) {
        try {
            Connection con = DBUtil.getConnection();
            PreparedStatement pst = con.prepareStatement("select * from money where userId=?");
            pst.setInt(1, userId);
            ResultSet rs = pst.executeQuery();
            List<BillBook> billBookList = new ArrayList<>();
            while (rs.next()) {
                BillBook billBook = new BillBook();
                billBook.setBid(rs.getInt("mid"));
                billBook.setBname(rs.getString("mname"));
                billBook.setWay(rs.getString("mway"));
                billBook.setMoney(rs.getFloat("mnums"));
                billBook.setBcdate(rs.getDate("mtime"));
                CategoryDao categoryDao = new CategoryDaoImpl();
                Category category = categoryDao.getCategoryById(rs.getInt("id"));
                billBook.setCategory(category);

                UserDao userDao = new UserDaoImpl();
                billBook.setUser(userDao.searchUserById(userId));
                billBookList.add(billBook);
            }
            //释放连接资源
            pst.close();
            rs.close();
            con.close();
            return billBookList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<BillBook> getBillBookByCategory(int categoryId, int userId) {
        try {
            Connection con = DBUtil.getConnection();
            PreparedStatement pst = con.prepareStatement("select * from money where (id=? and userId=?)");
            pst.setInt(1, categoryId);
            pst.setInt(2, userId);
            ResultSet rs = pst.executeQuery();
            List<BillBook> billBookList = new ArrayList<>();
            while (rs.next()) {
                BillBook billBook = new BillBook();
                billBook.setBid(rs.getInt("mid"));
                billBook.setBname(rs.getString("mname"));
                billBook.setWay(rs.getString("mway"));
                billBook.setMoney(rs.getFloat("mnums"));
                billBook.setBcdate(rs.getDate("mtime"));
                CategoryDao categoryDao = new CategoryDaoImpl();
                billBook.setCategory(categoryDao.getCategoryById(categoryId));

                UserDao userDao = new UserDaoImpl();
                billBook.setUser(userDao.searchUserById(userId));
                billBookList.add(billBook);
            }
            //释放连接资源
            pst.close();
            rs.close();
            con.close();
            return billBookList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<BillBook> getMonthIncome(int month, int userId) {
        try {
            Connection con = DBUtil.getConnection();
            PreparedStatement pst = con.prepareStatement("select * from money inner join moneycategory m on " +
                    "money.id = m.id where (month(mtime) =? and " +
                    "inorout='收入' and userId=?)");
            pst.setInt(1, month);
            pst.setInt(2, userId);
            ResultSet rs = pst.executeQuery();
            List<BillBook> billBookList = new ArrayList<>();
            while (rs.next()) {
                BillBook billBook = new BillBook();
                billBook.setBid(rs.getInt("mid"));
                billBook.setBname(rs.getString("mname"));
                billBook.setWay(rs.getString("mway"));
                billBook.setMoney(rs.getFloat("mnums"));
                billBook.setBcdate(rs.getDate("mtime"));
                CategoryDao categoryDao = new CategoryDaoImpl();
                Category category = categoryDao.getCategoryById(rs.getInt("id"));
                billBook.setCategory(category);

                UserDao userDao = new UserDaoImpl();
                User user = userDao.searchUserById(rs.getInt("userId"));
                billBook.setUser(user);
                billBookList.add(billBook);
            }
            //释放连接资源
            pst.close();
            rs.close();
            con.close();
            return billBookList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<BillBook> getMonthOutcome(int month, int userId) {
        try {
            Connection con = DBUtil.getConnection();
            PreparedStatement pst = con.prepareStatement("select * from money inner join moneycategory m on " +
                    "money.id = m.id where (month(mtime) =? and " +
                    "inorout='支出' and userId=?)");
            pst.setInt(1, month);
            pst.setInt(2, userId);
            ResultSet rs = pst.executeQuery();
            List<BillBook> billBookList = new ArrayList<>();
            while (rs.next()) {
                BillBook billBook = new BillBook();
                billBook.setBid(rs.getInt("mid"));
                billBook.setBname(rs.getString("mname"));
                billBook.setWay(rs.getString("mway"));
                billBook.setMoney(rs.getFloat("mnums"));
                billBook.setBcdate(rs.getDate("mtime"));
                CategoryDao categoryDao = new CategoryDaoImpl();
                Category category = categoryDao.getCategoryById(rs.getInt("id"));
                billBook.setCategory(category);

                UserDao userDao = new UserDaoImpl();
                User user = userDao.searchUserById(rs.getInt("userId"));
                billBook.setUser(user);
                billBookList.add(billBook);
            }
            //释放连接资源
            pst.close();
            rs.close();
            con.close();
            return billBookList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<BillBook> getYearIncome(int year, int userId) {
        try {
            Connection con = DBUtil.getConnection();
            PreparedStatement pst = con.prepareStatement("select * from money inner join moneycategory m on " +
                    "money.id = m.id where (YEAR(mtime)=? and " +
                    "inorout='收入' and userId=?)");
            pst.setInt(1, year);
            pst.setInt(2, userId);
            ResultSet rs = pst.executeQuery();
            List<BillBook> billBookList = new ArrayList<>();
            while (rs.next()) {
                BillBook billBook = new BillBook();
                billBook.setBid(rs.getInt("mid"));
                billBook.setBname(rs.getString("mname"));
                billBook.setWay(rs.getString("mway"));
                billBook.setMoney(rs.getFloat("mnums"));
                billBook.setBcdate(rs.getDate("mtime"));
                CategoryDao categoryDao = new CategoryDaoImpl();
                Category category = categoryDao.getCategoryById(rs.getInt("id"));
                billBook.setCategory(category);

                UserDao userDao = new UserDaoImpl();
                User user = userDao.searchUserById(rs.getInt("userId"));
                billBook.setUser(user);
                billBookList.add(billBook);
            }
            //释放连接资源
            pst.close();
            rs.close();
            con.close();
            return billBookList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<BillBook> getYearOutcome(int year, int userId) {
        try {
            Connection con = DBUtil.getConnection();
            PreparedStatement pst = con.prepareStatement("select * from money inner join moneycategory m on " +
                    "money.id = m.id where (year(mtime)=? and " +
                    "inorout='支出' and userId=?)");
            pst.setInt(1, year);
            pst.setInt(2, userId);
            ResultSet rs = pst.executeQuery();
            List<BillBook> billBookList = new ArrayList<>();
            while (rs.next()) {
                BillBook billBook = new BillBook();
                billBook.setBid(rs.getInt("mid"));
                billBook.setBname(rs.getString("mname"));
                billBook.setWay(rs.getString("mway"));
                billBook.setMoney(rs.getFloat("mnums"));
                billBook.setBcdate(rs.getDate("mtime"));
                CategoryDao categoryDao = new CategoryDaoImpl();
                Category category = categoryDao.getCategoryById(rs.getInt("id"));
                billBook.setCategory(category);

                UserDao userDao = new UserDaoImpl();
                User user = userDao.searchUserById(rs.getInt("userId"));
                billBook.setUser(user);
                billBookList.add(billBook);
            }
            //释放连接资源
            pst.close();
            rs.close();
            con.close();
            return billBookList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        BillBookDao billBookDao = new BillBookDaoImpl();
//        System.out.println(billBookDao.getBillBookList(1));
        System.out.println(billBookDao.getMonthOutcome(6, 69187354));
        System.out.println(billBookDao.getYearOutcome(2023, 69187354));
    }
}
