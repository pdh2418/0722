package com.yubin.dvd.service;

import com.yubin.dvd.bean.Dvd;
import com.yubin.dvd.util.JdbcUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DvdService {
    Scanner scanner=new Scanner(System.in);
    Connection con;
    Statement statement;
    ResultSet rs;
    public int add(Dvd d) {
        con= JdbcUtil.getCon();
        int i=0;
        try {
            statement=con.createStatement();
            i = statement.executeUpdate("INSERT into  dvd(id,name,state,date)VALUES(" +
                    d.getId() + ",'" + d.getName() + "','" + d.getState() + "','" + d.getDate() + "')");


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JdbcUtil.closeConn(con,statement,rs);
        }
        return i;
    }

    public List<Dvd> showDvd() {
        List<Dvd> dvds=new ArrayList<>();
        con=JdbcUtil.getCon();
        try {
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from dvd");
            while (resultSet.next()){
                Dvd dvd=new Dvd();
                dvd.setId(resultSet.getInt(1));
                dvd.setName(resultSet.getString(2));
                dvd.setState(resultSet.getString(3));
                dvd.setDate(resultSet.getString(4));
                dvds.add(dvd);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return dvds;
    }

    public void drop(int dvdId) {
        con=JdbcUtil.getCon();
        try {
            Statement statement = con.createStatement();
            statement.executeUpdate("delete from dvd where id="+dvdId+";");
            JdbcUtil.closeConn(con,statement,rs);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public int modifyDvd(int id3) {
        int i=0;
        con=JdbcUtil.getCon();
        Statement statement = null;
        try {
            statement = con.createStatement();
            System.out.println("请输入修改后的id");
            int newid=scanner.nextInt();
            System.out.println("请输入修改后的名字");
            String newname=scanner.next();
            System.out.println("请输入修改后的状态");
            String newstate=scanner.next();
            System.out.println("请输入修改后的日期");
            String newdate=scanner.next();

            statement.executeUpdate("UPDATE dvd SET ID='"+newid+"',name='" + newname +
                    "',state='" + newstate + "',date='"+newdate +"' WHERE id='" + id3 + "';");
            JdbcUtil.closeConn(con,statement,rs);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }



        return i;
    }
}
