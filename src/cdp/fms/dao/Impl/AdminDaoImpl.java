package cdp.fms.dao.Impl;

import cdp.fms.dao.AdminDao;
import cdp.fms.model.Admin;
import cdp.fms.uitl.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminDaoImpl implements AdminDao {
    @Override
    public Admin getAdminById(int adminId) {

        //获取连接
        Connection con = DBUtil.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            //执行查询并返回结果集
            pst = con.prepareStatement("select * from admin where id = ?");
            pst.setInt(1, adminId); //填充参数，注意参数编号从1开始
            rs = pst.executeQuery();
            Admin admin = new Admin();
            if (rs.next()) {
                admin.setId(rs.getInt("id"));
                admin.setAccount(rs.getString("account"));
                admin.setPsd(rs.getString("psd"));
            }
            //返回结果
            return admin;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //释放JDBC相关资源
            DBUtil.release(rs, pst, con);
        }
        return null;
    }

    @Override
    public Admin login(String account, String password) {
        try {
            //获取连接
            Connection con = DBUtil.getConnection();

            //执行查询并返回结果集
            PreparedStatement pst = con.prepareStatement("select * from admin where account=? and psd=?");
            pst.setString(1, account); //填充参数，注意参数编号从1开始
            pst.setString(2, password);
            ResultSet rs = pst.executeQuery();
            Admin admin = new Admin();
            if (rs.next()) {
                admin.setId(rs.getInt("id"));
                admin.setAccount(rs.getString("account"));

                //密码不写入（1、并没有使用 2、防止敏感信息泄露）
                //admin.setPassword(rs.getString("password"));

                //释放连接资源
                rs.close();
                pst.close();
                con.close();

                //返回结果
                return admin;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Admin> getAllAdmins() {
        //获取连接
        Connection con = DBUtil.getConnection();
        Statement st = null;
        ResultSet rs = null;
        try {
            //执行查询并返回结果集
            st = con.createStatement();
            rs = st.executeQuery("select * from admin");

            //封装结果为列表数据
            List<Admin> admins = new ArrayList<>();
            while (rs.next()) {
                Admin admin = new Admin();

                admin.setId(rs.getInt("id"));
                admin.setAccount(rs.getString("account"));

                admins.add(admin);
            }

            //返回结果
            return admins;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //释放连接资源
            DBUtil.release(rs, st, con);
        }

        return null;
    }

    @Override
    public boolean add(Admin admin) {
        try {
            //获取连接
            Connection con = DBUtil.getConnection();

            //执行查询并返回结果集
            PreparedStatement pst = con.prepareStatement("insert into admin(account, psd) values(?, ?)"
                    ,Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, admin.getAccount());
            pst.setString(2, admin.getPsd());

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
    public void update(Admin admin) {
        try {
            //获取连接
            Connection con = DBUtil.getConnection();

            PreparedStatement pst = con.prepareStatement("update admin set  account=?, psd=? where id=?");
            pst.setString(1, admin.getAccount());
            pst.setString(2, admin.getPsd());
            pst.setInt(3, admin.getId());

            pst.executeUpdate();

            //释放连接资源
            pst.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int adminId) {
        try {
            //获取连接
            Connection con = DBUtil.getConnection();

            PreparedStatement pst = con.prepareStatement("delete from admin where id=?");
            pst.setInt(1, adminId);

            pst.executeUpdate();

            //释放连接资源
            pst.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        AdminDao adminDao = new AdminDaoImpl();
        System.out.println(adminDao.getAdminById(1));
        System.out.println(adminDao.login("admin", "123456"));
    }
}
