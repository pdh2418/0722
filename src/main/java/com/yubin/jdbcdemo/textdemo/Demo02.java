package com.yubin.jdbcdemo.textdemo;

import com.yubin.jdbcdemo.util.JdbcUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Demo02 {
    public static void main(String[] args) {
        Connection con = JdbcUtil.getCon();
        try {
            Statement statement = con.createStatement();
            Scanner scanner = new Scanner(System.in);
            System.out.println("请输入id:");
            int id = scanner.nextInt();
            System.out.println("请输入名称");
            String name = scanner.next();
            int i = statement.executeUpdate("insert into teacher(Tid,Tname) VALUES ("+id+",'"+name+"');");
            System.out.println(i);

            con.close();
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
