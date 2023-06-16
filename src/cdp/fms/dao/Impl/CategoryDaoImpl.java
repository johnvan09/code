package cdp.fms.dao.Impl;

import cdp.fms.dao.CategoryDao;
import cdp.fms.model.Category;
import cdp.fms.model.User;
import cdp.fms.uitl.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDaoImpl implements CategoryDao {
    @Override
    public boolean addCategory(Category category) {
        try {
            //获取连接
            Connection con = DBUtil.getConnection();

            //执行查询并返回结果集
            PreparedStatement pst = con.prepareStatement("insert into moneycategory(inorout, cname) values(?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, category.getInorout());
            pst.setString(2, category.getCategoryName());

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
    public boolean isUserExisted(String key, String value) {
        try {
            //获取连接
            Connection con = DBUtil.getConnection();

            //执行查询并返回结果集
            PreparedStatement pst = con.prepareStatement("select * from moneycategory where " + key + "=?");
            pst.setString(1, value);

            ResultSet rs = pst.executeQuery();

            boolean result = rs.next(); //判断是否查询到数据

            //释放连接资源
            rs.close();
            pst.close();
            con.close();

            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean changeCategory(Category category) {
        try {
            //获取连接
            Connection con = DBUtil.getConnection();

            //执行查询并返回结果集
            PreparedStatement pst = con.prepareStatement("update moneycategory set inorout=?, cname=? where id=?");
            pst.setString(1, category.getInorout());
            pst.setString(2, category.getCategoryName());
            pst.setInt(3, category.getId());

            int result = pst.executeUpdate();

            //释放连接资源
            pst.close();
            con.close();

            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Category> getAllCategories() {
        try {
            //获取连接
            Connection con = DBUtil.getConnection();

            //执行查询并返回结果集
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from moneycategory");

            //封装结果为列表数据
            List<Category> categories = new ArrayList<>();
            while (rs.next()) {
                Category category = new Category();

                category.setId(rs.getInt("id"));
                category.setInorout(rs.getString("inorout"));
                category.setCategoryName(rs.getString("cname"));


                categories.add(category);
            }

            //释放连接资源
            rs.close();
            st.close();
            con.close();

            //返回结果
            return categories;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean deleteCategoryById(int categoryId) {
        try {
            //获取连接
            Connection con = DBUtil.getConnection();

            //执行查询并返回结果集
            PreparedStatement pst = con.prepareStatement("delete from moneycategory where id=?");
            pst.setInt(1, categoryId);

            ResultSet rs = pst.executeQuery();

            boolean result = rs.next(); //判断是否删除数据

            //释放连接资源
            rs.close();
            pst.close();
            con.close();

            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public Category getCategoryById(int categoryId) {
        try {
            //获取连接
            Connection con = DBUtil.getConnection();

            //执行查询并返回结果集
            PreparedStatement pst = con.prepareStatement("select * from moneycategory where id=?  ");
            pst.setInt(1, categoryId);

            ResultSet rs = pst.executeQuery();
            Category category = null;
            if (rs.next()) {
                category = new Category();
                category.setId(rs.getInt("id"));
                category.setInorout(rs.getString("inorout"));
                category.setCategoryName(rs.getString("cname"));

            }

            //释放连接资源
            rs.close();
            pst.close();
            con.close();

            //返回结果
            return category;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        CategoryDao categoryDao = new CategoryDaoImpl();
//        System.out.println(categoryDao.getCategoryById(1));
//        System.out.println(categoryDao.getAllCategories());
        System.out.println(categoryDao.isUserExisted("id", String.valueOf(1)));
    }
}
