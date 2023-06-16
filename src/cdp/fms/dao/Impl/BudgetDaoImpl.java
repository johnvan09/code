package cdp.fms.dao.Impl;

import cdp.fms.dao.BudgetDao;
import cdp.fms.dao.CategoryDao;
import cdp.fms.dao.UserDao;
import cdp.fms.model.Budget;
import cdp.fms.model.Category;
import cdp.fms.model.User;
import cdp.fms.uitl.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BudgetDaoImpl implements BudgetDao {
    @Override
    public boolean addBudget(Budget budget) {
        try {
            Connection con = DBUtil.getConnection();

            PreparedStatement pst = con.prepareStatement("insert into budget(bname, binfo, bmoney, btime, id, userId" +
                    "  ) values(?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, budget.getBuname());
            pst.setString(2, budget.getBinfo());
            pst.setFloat(3, budget.getBmoney());
            pst.setDate(4, (Date) budget.getBtime());
            pst.setInt(5, budget.getCategory().getId());
            pst.setInt(6, budget.getUser().getId());


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
    public Budget getBudgetById(int id) {
        try {
            Connection con = DBUtil.getConnection();
            PreparedStatement pst = con.prepareStatement("select * from budget where bid=?");
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();

            Budget budget = new Budget();
            if (rs.next()) {

                budget.setBuid(rs.getInt("bid"));
                budget.setBuname(rs.getString("bname"));
                budget.setBinfo(rs.getString("binfo"));
                budget.setBmoney(rs.getFloat("bmoney"));
                budget.setBtime(rs.getDate("btime"));

                CategoryDao categoryDao = new CategoryDaoImpl();
                Category category = categoryDao.getCategoryById(rs.getInt("id"));
                budget.setCategory(category);

                UserDao userDao = new UserDaoImpl();
                User user = userDao.searchUserById(rs.getInt("userId"));
                budget.setUser(user);
            }
            //释放连接资源
            pst.close();
            rs.close();
            con.close();
            return budget;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Budget> getBudget(int userId) {
        try {
            Connection con = DBUtil.getConnection();
            PreparedStatement pst = con.prepareStatement("select * from budget where userId=?");
            pst.setInt(1, userId);
            ResultSet rs = pst.executeQuery();
            List<Budget> budgetList = new ArrayList<>();
            while (rs.next()) {
                Budget budget = new Budget();
                budget.setBuid(rs.getInt("bid"));
                budget.setBuname(rs.getString("bname"));
                budget.setBinfo(rs.getString("binfo"));
                budget.setBmoney(rs.getFloat("bmoney"));
                budget.setBtime(rs.getDate("btime"));

                CategoryDao categoryDao = new CategoryDaoImpl();
                Category category = categoryDao.getCategoryById(rs.getInt("id"));
                budget.setCategory(category);

                UserDao userDao = new UserDaoImpl();
                budget.setUser(userDao.searchUserById(userId));
                budgetList.add(budget);
            }
            //释放连接资源
            pst.close();
            rs.close();
            con.close();
            return budgetList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean deleteBudget(int id) {
        try {
            //获取连接
            Connection con = DBUtil.getConnection();

            PreparedStatement pst = con.prepareStatement("delete from budget where bid=?");
            pst.setInt(1, id);

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
    public boolean changeBudget(Budget budget) {
        try {
            Connection con = DBUtil.getConnection();
            PreparedStatement pst = con.prepareStatement("update budget set bname=?,binfo=?,bmoney=?,btime=?,id=? " +
                    "where bid=?");

            pst.setString(1, budget.getBuname());
            pst.setString(2, budget.getBinfo());
            pst.setFloat(3, budget.getBmoney());
            pst.setDate(4, (Date) budget.getBtime());
            pst.setInt(5, budget.getCategory().getId());
            pst.setInt(6, budget.getBuid());

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
    public List<Budget> getBudgetList(int categoryId, int userId) {
        try {
            Connection con = DBUtil.getConnection();
            PreparedStatement pst = con.prepareStatement("select * from budget where (userId=? and id=?)");
            pst.setInt(1, userId);
            pst.setInt(2, categoryId);
            ResultSet rs = pst.executeQuery();
            List<Budget> budgetList = new ArrayList<>();
            while (rs.next()) {
                Budget budget = new Budget();
                budget.setBuid(rs.getInt("bid"));
                budget.setBuname(rs.getString("bname"));
                budget.setBinfo(rs.getString("binfo"));
                budget.setBmoney(rs.getFloat("bmoney"));
                budget.setBtime(rs.getDate("btime"));

                CategoryDao categoryDao = new CategoryDaoImpl();
                Category category = categoryDao.getCategoryById(rs.getInt("id"));
                budget.setCategory(category);

                UserDao userDao = new UserDaoImpl();
                budget.setUser(userDao.searchUserById(userId));
                budgetList.add(budget);
            }
            //释放连接资源
            pst.close();
            rs.close();
            con.close();
            return budgetList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
