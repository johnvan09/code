package cdp.fms.dao.Impl;

import cdp.fms.dao.WishListDao;
import cdp.fms.model.Wishlist;
import cdp.fms.uitl.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.sql.Types.NULL;

public class WishListDaoImpl implements WishListDao {
    @Override
    public boolean addWish(Wishlist wishlist, int userId) {
        try {
            //获取连接
            Connection con = DBUtil.getConnection();

            Random r = new Random();
            if (wishlist.getWid() == NULL) {
                int id = r.nextInt(100000000);
                if (isWishExisted("wid", String.valueOf(id))) {
                    wishlist.setWid(id);
                }
            }

            //执行查询并返回结果集
            PreparedStatement pst = con.prepareStatement("insert into wishlist(wname, wmoney, whmoney, wcdate, wstatus" +
                    ", wfdate,wid) values(?,?,?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, wishlist.getWname());
            pst.setFloat(2, wishlist.getwAllMoney());
            pst.setFloat(3, wishlist.getWhaveMoney());
            pst.setDate(4, (Date) wishlist.getWcdate());
            pst.setString(5, wishlist.getWstatus());
            pst.setDate(6, (Date) wishlist.getWfdate());
            pst.setInt(7, wishlist.getWid());
            pst.executeUpdate();

            PreparedStatement pst1 = con.prepareStatement("insert into invite(userId1, wid) values(?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            pst1.setInt(1, userId);
            pst1.setInt(2, wishlist.getWid());
            int result = pst1.executeUpdate();

            //释放连接资源
            pst.close();
            pst1.close();
            con.close();

            return result > 0; //大于0表示成功创建一行
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean isWishExisted(String key, String valueOf) {
        try {
            //获取连接
            Connection con = DBUtil.getConnection();

            //执行查询并返回结果集
            PreparedStatement pst = con.prepareStatement("select * from wishlist where " + key + "=?");
            pst.setString(1, valueOf);

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
    public boolean inviteFriendsWishlist(Wishlist wishlist, int userId1, int userId2) {
        try {
            //获取连接
            Connection con = DBUtil.getConnection();

            //执行查询并返回结果集
            PreparedStatement pst = con.prepareStatement("insert into wishlist(wname, wmoney, whmoney, wcdate, wstatus" +
                    ", wfdate) values(?,?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, wishlist.getWname());
            pst.setFloat(2, wishlist.getwAllMoney());
            pst.setFloat(3, wishlist.getWhaveMoney());
            pst.setDate(4, (Date) wishlist.getWcdate());
            pst.setString(5, wishlist.getWstatus());
            pst.setDate(6, (Date) wishlist.getWfdate());

            pst.executeUpdate();

            PreparedStatement pst1 = con.prepareStatement("insert into invite(userId1,userId2, wid) values(?,?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            pst1.setInt(1, userId1);
            pst1.setInt(2, userId2);
            pst1.setInt(3, wishlist.getWid());
            int result = pst1.executeUpdate();

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
    public List<Wishlist> getWishlistByUserId(int userId) {
        try {
            //获取连接
            Connection con = DBUtil.getConnection();

            //执行查询并返回结果集
            PreparedStatement pst = con.prepareStatement("select * from wishlist join invite i " +
                    "on wishlist.wid = i.wid where (i.userId1=? or i.userId2=?)");
            pst.setInt(1, userId);
            pst.setInt(2, userId);
            ResultSet rs = pst.executeQuery();
            List<Wishlist> wishlists = new ArrayList<>();
            while (rs.next()) {
                Wishlist wishlist = new Wishlist();
                wishlist.setWname(rs.getString("wname"));
                wishlist.setWid(rs.getInt("wid"));
                wishlist.setwAllMoney(rs.getFloat("wmoney"));
                wishlist.setWhaveMoney(rs.getFloat("whmoney"));
                wishlist.setWcdate(rs.getDate("wcdate"));
                wishlist.setWstatus(rs.getString("wstatus"));
                wishlist.setWfdate(rs.getDate("wfdate"));
                wishlists.add(wishlist);
            }

            //释放连接资源
            rs.close();
            pst.close();
            con.close();

            //返回结果
            return wishlists;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean changeWishlist(Wishlist wishlist) {
        try {
            Connection con = DBUtil.getConnection();
            PreparedStatement pst = con.prepareStatement("update wishlist set wname=?,wmoney=?,whmoney=?,wcdate=?" +
                    ",wstatus=? ,wfdate=? where wid=?");

            pst.setString(1, wishlist.getWname());
            pst.setFloat(2, wishlist.getwAllMoney());
            pst.setFloat(3, wishlist.getWhaveMoney());
            pst.setDate(4, (Date) wishlist.getWcdate());
            pst.setString(5, wishlist.getWstatus());
            pst.setDate(6, (Date) wishlist.getWfdate());
            pst.setInt(7, wishlist.getWid());

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
    public boolean deleteWishlist(int id) {
        try {
            //获取连接
            Connection con = DBUtil.getConnection();

            PreparedStatement pst = con.prepareStatement("delete from wishlist where wid=?");
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
    public Wishlist getWishlistById(int wid) {
        try {
            Connection con = DBUtil.getConnection();
            PreparedStatement pst = con.prepareStatement("select * from wishlist where wid=?");
            pst.setInt(1, wid);
            ResultSet rs = pst.executeQuery();
            Wishlist budget = new Wishlist();
            if (rs.next()) {

                budget.setWid(rs.getInt("wid"));
                budget.setWname(rs.getString("wname"));
                budget.setwAllMoney(rs.getFloat("wmoney"));
                budget.setWhaveMoney(rs.getFloat("whmoney"));
                budget.setWcdate(rs.getDate("wcdate"));
                budget.setWstatus(rs.getString("wstatus"));
                budget.setWfdate(rs.getDate("wfdate"));


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

    public static void main(String[] args) {
        Wishlist wishlist = new Wishlist();
        WishListDao wishListDao = new WishListDaoImpl();
//        wishlist.setWid(123);
//        System.out.println(wishListDao.addWish(wishlist,1));
        System.out.println(wishListDao.getWishlistByUserId(1));
    }
}
