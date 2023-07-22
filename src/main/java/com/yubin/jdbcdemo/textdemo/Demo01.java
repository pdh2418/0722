package com.yubin.jdbcdemo.textdemo;

import com.yubin.jdbcdemo.util.JdbcUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Demo01 {
    public static void main(String[] args){
        Connection con = JdbcUtil.getCon();
        try {
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from teacher");
            while (resultSet.next()){
                int id = resultSet.getInt(1);
                String adminname = resultSet.getString(2);
                System.out.println("id:"+id+"   姓名:"+adminname);
            }
            JdbcUtil.closeConn(con,statement,resultSet);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
