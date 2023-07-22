package com.yubin.apsfc.service.imp;

import com.yubin.apsfc.dean.Order;
import com.yubin.apsfc.dean.User;
import com.yubin.apsfc.service.UserService;
import com.yubin.apsfc.util.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserServiceImp implements UserService {
    @Override
    public User loginUser(String username, String password) {
        Connection con = JdbcUtils.getCon();
        String sql="select * from users where phone=? and pwd=?";
        User user=null;
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,password);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                user=new User();
                user.setId(resultSet.getInt(1));
                user.setUsername(resultSet.getString(2));
                user.setUserpassword(resultSet.getString(3));
                user.setSex(resultSet.getString(4));
                user.setAddress(resultSet.getString(5));
                user.setPhone(resultSet.getString(6));
                user.setEmail(resultSet.getString(7));
                user.setGrade(resultSet.getString(8));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }

    @Override
    public int addUser(User users) {
        Connection con = JdbcUtils.getCon();
        int i=0;
        String sql="insert into users(name,pwd,sex,address,phone,email,grade) values(?,?,?,?,?,?,?) ";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, users.getUsername());
            preparedStatement.setString(2, users.getUserpassword());
            preparedStatement.setString(3, users.getSex());

            preparedStatement.setString(4, users.getAddress());
            preparedStatement.setString(5, users.getPhone());
            preparedStatement.setString(6, users.getEmail());
            preparedStatement.setString(7, users.getGrade());
            i= preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return i;
    }

    @Override
    public String selectEmailByPhone(String phone) {
        Connection con = JdbcUtils.getCon();
        String sql="select email from users where phone=?";
        String email=null;
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1,phone);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                email=resultSet.getString(1);
                System.out.println(email);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return email;
    }

    @Override
    public int updateUserPassWord(String newpassword, String phone, String email) {
        Connection con = JdbcUtils.getCon();
        int i=0;
        String sql="update users set pwd=? where phone=? and email =? ";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, newpassword );
            preparedStatement.setString(2, phone);
            preparedStatement.setString(3, email);
            i = preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return i;
    }

    @Override
    public ArrayList<Order> showOrders(int id) {
        Connection con = JdbcUtils.getCon();
        ArrayList<Order> orders = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = con.prepareStatement("SELECT u.name,m.name,o.num,o.exam FROM torder o INNER JOIN menus m ON o.menuId=m.id INNER JOIN users u ON o.userID=u.id WHERE o.userId=?; ");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Order order = new Order();
                order.setUsername(resultSet.getString(1));
                order.setFooName(resultSet.getString(2));
                order.setFoodNum(resultSet.getInt(3));
                order.setFoodTime(resultSet.getString(4));
                orders.add(order);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return orders;
    }

    @Override
    public String selectGradeById(int userid) {
        Connection con = JdbcUtils.getCon();
        String grade =null;
        try {
            PreparedStatement preparedStatement = con.prepareStatement("select grade from users where id =?");
            preparedStatement.setInt(1, userid);
            System.out.println(userid);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                grade = resultSet.getString(1);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return grade;
    }

    @Override
    public int selectUserMoneyById(int userid) {
        Connection con = JdbcUtils.getCon();
        int money=0;
        try {

            PreparedStatement preparedStatement = con.prepareStatement("select usermoney from amout where userid=?");
            preparedStatement.setInt(1, userid);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                money=resultSet.getInt(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return money;
    }

    @Override
    public int rechar(int id, int money) {
        Connection con = JdbcUtils.getCon();
        PreparedStatement preparedStatement = null;
        int i=0;
        try {
            preparedStatement = con.prepareStatement("UPDATE amout SET usermoney=usermoney+? WHERE userid=?;");
            preparedStatement.setInt(1,money);
            preparedStatement.setInt(2,id);
            i = preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return i;
    }

    @Override
    public int showSumMoney(int id) {
        Connection con = JdbcUtils.getCon();
        int i=0;
        try {
            PreparedStatement preparedStatement = con.prepareStatement("select usersummoney from amout where userid=?");
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                i=resultSet.getInt(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return i;
    }
}
