package cdp.fms.dao.Impl;

import cdp.fms.dao.UserDao;
import cdp.fms.model.User;
import cdp.fms.uitl.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.sql.Types.NULL;

public class UserDaoImpl implements UserDao {
    @Override
    public User loginUser(String account, String psd) {
        try {
            //获取连接
            Connection con = DBUtil.getConnection();

            //执行查询并返回结果集
            PreparedStatement pst = con.prepareStatement("select * from user where (account=? or phone=? or email=?) " +
                    "and psd=? and forbidden=?");
            pst.setString(1, account);
            pst.setString(2, account);
            pst.setString(3, account);
            pst.setString(4, psd);
            pst.setInt(5, 0);//1：封号；0：正常使用
            ResultSet rs = pst.executeQuery();

            User user = null;
            if (rs.next()) {
                user = new User();
                user.setAccount(rs.getString("account"));
                user.setId(rs.getInt("userId"));
                user.setName(rs.getString("name"));
                user.setGender(rs.getString("gender"));
                user.setPhone(rs.getString("phone"));
                user.setEmail(rs.getString("email"));
                user.setPhoto(rs.getString("photo"));
            }

            //释放连接资源
            rs.close();
            pst.close();
            con.close();

            //返回结果
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean addUser(User user) {
        try {
            //获取连接
            Connection con = DBUtil.getConnection();


            //执行查询并返回结果集
            PreparedStatement pst = con.prepareStatement("insert into user(account, psd, name, gender, phone, email," +
                    " forbidden,photo ,userId) values(?,?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, user.getAccount());
            pst.setString(2, user.getPsd());
            pst.setString(3, user.getName());
            pst.setString(4, user.getGender());
            pst.setString(5, user.getPhone());
            pst.setString(6, user.getEmail());
            pst.setInt(7, 0);//1：封号；0：正常使用
            pst.setString(8, user.getPhoto());
            pst.setInt(9,user.getId());
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
            PreparedStatement pst = con.prepareStatement("select * from user where " + key + "=?");
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
    public boolean changeUser(User user) {
        try {
            //获取连接
            Connection con = DBUtil.getConnection();

            //执行查询并返回结果集
            PreparedStatement pst = con.prepareStatement("update user set name=?, photo=?, gender=?, email=?, " +
                    "phone=? where account=?");
            pst.setString(1, user.getName());
            pst.setString(2, user.getPhoto());
            pst.setString(3, user.getGender());
            pst.setString(4, user.getEmail());
            pst.setString(5, user.getPhone());
            pst.setString(6, user.getAccount());

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
    public boolean changePsd(String account, String psd) {
        try {
            //获取连接
            Connection con = DBUtil.getConnection();

            //执行查询并返回结果集
            PreparedStatement pst = con.prepareStatement("update user set psd=? where account=?");
            pst.setString(1, psd);
            pst.setString(2, account);

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
    public boolean forbiddenUser(int userId) {
        try {
            //获取连接
            Connection con = DBUtil.getConnection();

            //执行查询并返回结果集
            PreparedStatement pst = con.prepareStatement("update user set forbidden=? where userId=?");
            pst.setInt(1, 1);//1:禁用 0:正常使用
            pst.setInt(2, userId);

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
    public User searchUserById(int userId) {
        try {
            //获取连接
            Connection con = DBUtil.getConnection();

            //执行查询并返回结果集
            PreparedStatement pst = con.prepareStatement("select * from user where (userId=? and forbidden = ?)");
            pst.setInt(1, userId);
            pst.setInt(2, 0);
            ResultSet rs = pst.executeQuery();
            User user = null;
            if (rs.next()) {
                user = new User();
                user.setAccount(rs.getString("account"));
                user.setId(rs.getInt("userId"));
                user.setName(rs.getString("name"));
                user.setGender(rs.getString("gender"));
                user.setPhone(rs.getString("phone"));
                user.setEmail(rs.getString("email"));
                user.setPhoto(rs.getString("photo"));
            }

            //释放连接资源
            rs.close();
            pst.close();
            con.close();

            //返回结果
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User getUserByAccount(String account) {
        try {
            //获取连接
            Connection con = DBUtil.getConnection();

            //执行查询并返回结果集
            PreparedStatement pst = con.prepareStatement("select * from user where (account=? and forbidden = ?)");
            pst.setString(1, account);
            pst.setInt(2, 0);
            ResultSet rs = pst.executeQuery();
            User user = null;
            if (rs.next()) {
                user = new User();
                user.setAccount(rs.getString("account"));
                user.setId(rs.getInt("userId"));
                user.setName(rs.getString("name"));
                user.setGender(rs.getString("gender"));
                user.setPhone(rs.getString("phone"));
                user.setEmail(rs.getString("email"));
                user.setPhoto(rs.getString("photo"));
            }

            //释放连接资源
            rs.close();
            pst.close();
            con.close();

            //返回结果
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        try {
            //获取连接
            Connection con = DBUtil.getConnection();

            //执行查询并返回结果集
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from user");

            //封装结果为列表数据
            List<User> users = new ArrayList<>();
            while (rs.next()) {
                User user = new User();

                user.setId(rs.getInt("userId"));
                user.setAccount(rs.getString("account"));
                user.setPhoto(rs.getString("photo"));
                user.setForbidden(rs.getInt("forbidden"));
                user.setName(rs.getString("name"));
                user.setPhone(rs.getString("phone"));
                user.setEmail(rs.getString("email"));
                user.setGender(rs.getString("gender"));

                users.add(user);
            }

            //释放连接资源
            rs.close();
            st.close();
            con.close();

            //返回结果
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean makeFriends(int userId1, int userId2) {
        try {
            if (userId1 == userId2) {
                return false;
            } else {
                //获取连接
                Connection con = DBUtil.getConnection();

                //执行查询并返回结果集
                PreparedStatement pst = con.prepareStatement("insert into friends(userId, friendId,forbidden) values(?,?,?)");
                pst.setInt(1, userId1);
                pst.setInt(2, userId2);
                pst.setBoolean(3, false);

                int result = pst.executeUpdate();

                //释放连接资源
                pst.close();
                con.close();

                return result > 0; //大于0表示成功创建一行
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean overFriend(int userId1, int userId2) {
        try {
            //获取连接
            Connection con = DBUtil.getConnection();

            //执行查询并返回结果集
            PreparedStatement pst = con.prepareStatement("update friends set forbidden=? where (userId=? and friendId=?)");
            pst.setBoolean(1, true);//true:禁用 false:正常使用
            pst.setInt(2, userId1);
            pst.setInt(3, userId2);

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
    public List<User> getFriendsById(int userId) {
        try {
            //获取连接
            Connection con = DBUtil.getConnection();

            //执行查询并返回结果集
            Statement st = con.createStatement();
            PreparedStatement pst = con.prepareStatement("select * from user " +
                    "join friends f on user.userId = f.userId " +
                    "where f.userId=?");
            pst.setInt(1, userId);


            ResultSet rs = pst.executeQuery();
            //封装结果为列表数据
            List<User> users = new ArrayList<>();
            while (rs.next()) {
                User user = new User();

                user.setId(rs.getInt("userId"));
                user.setAccount(rs.getString("account"));
                user.setPhoto(rs.getString("photo"));
                user.setForbidden(rs.getInt("forbidden"));
                user.setName(rs.getString("name"));
                user.setPhone(rs.getString("phone"));
                user.setEmail(rs.getString("email"));
                user.setGender(rs.getString("gender"));

                users.add(user);
            }

            //释放连接资源
            rs.close();
            st.close();
            con.close();

            //返回结果
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void main(String[] args) {
        UserDao userDao = new UserDaoImpl();

        User user=new User();
        user.setId(123456);
        user.setAccount("test");
        user.setPsd("123456");
        user.setGender("男");
        user.setName("test");
        user.setPhone("12345678910");
        user.setEmail("123456@aa.com");
//        System.out.println(userDao.loginUser("user1", "123456"));
//        System.out.println(userDao.getAllUsers());
//        User user = userDao.searchUserById(1);
//        System.out.println(userDao.changeUser(user));
//        System.out.println(userDao.getFriendsById(1));
        System.out.println(userDao.addUser(user));
    }

}
