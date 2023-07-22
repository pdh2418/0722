package com.yubin.dvd.util;

import java.sql.*;

public class JdbcUtil {
    public static Connection getCon(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("成功");
        } catch (ClassNotFoundException e) {
            System.out.println("失败");
        }


        String url="jdbc:mysql://localhost:3306/practice?user=root&password=123456&useSSL=false";

        Connection connection=null;
        try {
            connection = DriverManager.getConnection(url);
            System.out.println("获取链接成功");
        } catch (SQLException throwables) {
            System.out.println("获取链接失败");

            throwables.printStackTrace();
        }
        return connection;
    }


    public static void closeConn(Connection conn, Statement st, ResultSet rs){
        if(rs!=null){
            try {
                rs.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        try {
            st.close();
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
