package com.yubin.jdbcdemo.textdemo;


import com.yubin.jdbcdemo.util.JdbcUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author wap
 * @version 1.0.0
 * @data 2023/7/7 15:53
 */
public class Demo06shiwu {
    public static void main(String[] args) {
        Connection con = JdbcUtil.getCon();


        try {

            Statement statement = con.createStatement();

            //关闭自动提交

            con.setAutoCommit(false);

            String sql1="UPDATE bank SET cusmoney=cusmoney-500 WHERE cusname='张三';";
            int i =statement.executeUpdate(sql1);
            System.out.println("qeqq");

            String sql2="UPDATE bank SET cusmoney=cusmoney+500 WHERE cusname='李四';";
            int i2=statement.executeUpdate(sql2);
            System.out.println("sssss");

            con.commit();
            System.out.println(i);
            System.out.println(i2);
            System.out.println("提交");
        } catch (SQLException throwables) {
            try {
                con.rollback();
                System.out.println("回滚");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            throwables.printStackTrace();
        }

        System.out.println("关闭连接");

    }
}
