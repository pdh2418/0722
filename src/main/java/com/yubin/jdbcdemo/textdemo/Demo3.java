package com.yubin.jdbcdemo.textdemo;

import com.yubin.jdbcdemo.util.JdbcUtil;

import java.sql.*;

public class Demo3 {


//    public static void main(String[] args) {
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            String url="jdbc:mysql://localhost:3306/practice?user=root&password=123456&useSSL=false";
//            Connection connection = DriverManager.getConnection(url);
//            Statement statement = connection.createStatement();
//            int i = statement.executeUpdate("UPDATE bank SET money=money-500 WHERE name='张三';");
//
////            int i = statement.executeUpdate("insert into bank(name,money) values ('王五',8000);");
//
//
//            System.out.println(i);
//            statement.close();
//            connection.close();
//
//
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }
    public static void main(String[] args) {
        Connection con = JdbcUtil.getCon();
        try {
          Statement statement = con.createStatement();
          con.setAutoCommit(false);
          statement.executeUpdate("UPDATE bank SET money=money-500 WHERE name='张三';");
          statement.executeUpdate("UPDATE bank SET moneys=money+500 WHERE name='李四';");


            con.commit();



        } catch (SQLException throwables) {
            try {
                con.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            throwables.printStackTrace();
        }

    }

    }
