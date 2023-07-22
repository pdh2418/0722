package com.yubin.apsfc.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * @author wap
 * @version 1.0.0
 * @data 2023/7/18 15:07
 */
public class JdbcUtils {
    private static  String driver;
    private static  String url;
    private static  String username;
    private static  String userpassword;


    static {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("db.properties"));
            driver=properties.getProperty("driver");
            url = properties.getProperty("url");
            username=properties.getProperty("username");
            userpassword= properties.getProperty("userpassword");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static Connection getCon(){

        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url,username,userpassword);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }



    public static void closeCon(Connection conn, Statement st, ResultSet rs){
        if (rs!=null){
            try {
                rs.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        if (st!=null){
            try {
                st.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        if (conn!=null){
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

}
