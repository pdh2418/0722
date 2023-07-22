package com.yubin.apsfc.service;

import com.yubin.apsfc.dean.Menus;

import java.util.ArrayList;

public interface OrderService {
    ArrayList<Menus> showMenus();

    //往订单表里面添加数据
    int addOrder(int userid, int foodId, int foodNum, String format);


    int selectPriceOneByFoodId(int foodId);

    int selectPriceByFoodId(int foodId);

    int countSumMoney(int price1, int foodNum);


    int updateUserMoneyByIdAndAddOrder(int userid, int foodId, int foodNum, String format, int sumMoney);
}
