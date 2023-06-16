package cdp.fms.uitl;

import java.sql.*;

public class DBUtil {
    private static final String url = "jdbc:mysql://localhost/fms?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=Asia/Shanghai";
    private static final String user = "root";
    private static final String password = "root";

    /**
     * 获取数据库连接
     */
    public static Connection getConnection() {
        // 获取连接,URL
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, user, password);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return con;
    }

    /**
     * * 释放JDBC相关资源
     */
    public static void release(ResultSet rs, Statement st, Connection conn) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (st != null) {
            try {
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws SQLException {
        Connection conn = DBUtil.getConnection();
        //获取数据
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("select * from moneycategory");// 执行SQL查询
        while (rs.next()) {
            int id = rs.getInt("id");
            String inorout = rs.getString("inorout");
            String name = rs.getString("cname");
            System.out.println("编号：" + id + "收入/支出：" + inorout + ", 类别：" + name);
        }

        //释放连接资源
        DBUtil.release(rs, st, conn);
    }

}
