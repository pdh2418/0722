package com.yubin.apsfc.service.imp;

import com.yubin.apsfc.dean.Admin;
import com.yubin.apsfc.service.AdminService;
import com.yubin.apsfc.util.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class AdminServiceImp implements AdminService {
    Scanner scanner=new Scanner(System.in);

    @Override
    public void showmin() {
        Connection con = JdbcUtils.getCon();
        try {
            PreparedStatement preparedStatement = con.prepareStatement("select * from admin");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id=resultSet.getInt(1);
                String name=resultSet.getString(2);
                String password=resultSet.getString(3);
                System.out.println("管理员id"+id+"   "+"姓名"+name+"   "+"密码"+password);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public boolean exist(int id) {
        Connection connection = JdbcUtils.getCon();
        boolean flag=false;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("SELECT name FROM admin WHERE id=?;");
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                resultSet.getString(1);
                flag=true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return flag;
    }

    @Override
    public int delect(int id) {
        Connection connection = JdbcUtils.getCon();
        PreparedStatement preparedStatement = null;
        int i=0;
        try {
            preparedStatement = connection.prepareStatement("DELETE FROM admin WHERE id=?;");
            preparedStatement.setInt(1,id);
           i = preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return i;
    }

    @Override
    public int modif(int id) {
        System.out.print("请输入要修改的姓名: ");
        String name=scanner.next();
        System.out.print("请输入要修改的密码: ");
        String pwe=scanner.next();
        int i=0;
        Connection con = JdbcUtils.getCon();
        try {
            PreparedStatement preparedStatement = con.prepareStatement("update admin set name=?,pwd=? where id=?");
           preparedStatement.setString(1,name);
           preparedStatement.setString(2,pwe);
           i=preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return i;
    }

    @Override
    public int addmin(Admin admin) {
        Connection con = JdbcUtils.getCon();
        int i=0;
        try {
            PreparedStatement preparedStatement = con.prepareStatement("insert into admin(name,pwd)values (?,?)");
            preparedStatement.setString(1,admin.getAdminname());
            preparedStatement.setString(2,admin.getAdminpass());
            i=preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return i;
    }
}
