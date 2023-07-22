package com.yubin.apsfc.service.imp;

import com.yubin.apsfc.dean.Menus;
import com.yubin.apsfc.service.OrderService;
import com.yubin.apsfc.util.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderServiceImp implements OrderService {
    @Override
    public ArrayList<Menus> showMenus() {
        ArrayList<Menus> objects = new ArrayList<>();
        Connection con = JdbcUtils.getCon();
        try {
            PreparedStatement preparedStatement = con.prepareStatement("select m.id,m.name,t.name,m.burden,m.brief,m.price,m.price1 FROM menus m INNER JOIN types t on m.typeid=t.id;");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Menus menus = new Menus();
                menus.setMenuid(resultSet.getInt(1));
                menus.setMenuName(resultSet.getString(2));
                menus.setTypename(resultSet.getString(3));
                menus.setBurden(resultSet.getString(4));
                menus.setBrief(resultSet.getString(5));
                menus.setPrice(resultSet.getInt(6));
                menus.setPricel(resultSet.getInt(7));
                objects.add(menus);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return objects;
    }

    @Override
    public int addOrder(int userid, int foodId, int foodNum, String format) {
        Connection con = JdbcUtils.getCon();
        int i=0;
        String sql="insert into torder(userID,menuID,num,exam) values(?,?,?,?);";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, userid);
            preparedStatement.setInt(2, foodId);
            preparedStatement.setInt(3, foodNum);
            preparedStatement.setString(4, format);
            i = preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return i;
    }

    @Override
    public int selectPriceOneByFoodId(int foodId) {
        Connection con = JdbcUtils.getCon();
        int price1 =0;
        try {
            PreparedStatement preparedStatement = con.prepareStatement("select price1 from menus where  id =?");
            preparedStatement.setInt(1, foodId);
            ResultSet resultSet = preparedStatement.executeQuery();
            //查询结果是一行的话 才能给这个价格赋值
            while (resultSet.next()){
                price1=resultSet.getInt(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return price1;
    }

    @Override
    public int countSumMoney(int price1, int foodNum) {
        return price1*foodNum;
    }

    @Override
    public int updateUserMoneyByIdAndAddOrder(int userid, int foodId, int foodNum, String format, int sumMoney) {
        Connection con = JdbcUtils.getCon();
        int i=0;
        try {
            con.setAutoCommit(false);
            String sql="insert into torder(userID,menuID,num,exam)values(?,?,?,?)";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1,userid);
            preparedStatement.setInt(2,foodId);
            preparedStatement.setInt(3,foodNum);
            preparedStatement.setString(4,format);
            i=preparedStatement.executeUpdate();
            PreparedStatement preparedStatement1 = con.prepareStatement("update amout set usermoney=usermoney-? ,usersummoney=usersummoney+? where userid=?");
            preparedStatement1.setInt(1,sumMoney);
            preparedStatement1.setInt(2,sumMoney);
            preparedStatement1.setInt(3,userid);
            int i1 = preparedStatement1.executeUpdate();
            i=i1+i;
            con.commit();
        } catch (SQLException throwables) {
            try {
                con.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            throwables.printStackTrace();
        }
        return i;
    }

    @Override
    public int selectPriceByFoodId(int foodId) {
        Connection con = JdbcUtils.getCon();
        int price =0;
        try {
            PreparedStatement preparedStatement = con.prepareStatement("select price from menus where  id =?");
            preparedStatement.setInt(1, foodId);
            ResultSet resultSet = preparedStatement.executeQuery();
            //查询结果是一行的话 才能给这个价格赋值
            while (resultSet.next()){
                price = resultSet.getInt(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return price;
    }
}
