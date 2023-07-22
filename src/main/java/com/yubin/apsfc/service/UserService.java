package com.yubin.apsfc.service;

import com.yubin.apsfc.dean.Order;
import com.yubin.apsfc.dean.User;

import java.util.ArrayList;

public interface UserService {
    public User loginUser(String username,String password);

    int addUser(User users);

    String selectEmailByPhone(String phone);

    int updateUserPassWord(String newpassword,String phone,String email);

    ArrayList<Order> showOrders(int id);

    //根据用户id获取用户等级
    String selectGradeById(int userid);

    //根据用户id查找余额
    int selectUserMoneyById(int userid);

    int rechar(int id, int money);

    int showSumMoney(int id);
}
