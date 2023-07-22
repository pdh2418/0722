package com.yubin.apsfc.view;

import com.yubin.apsfc.dean.Menus;
import com.yubin.apsfc.dean.Order;
import com.yubin.apsfc.dean.User;
import com.yubin.apsfc.service.OrderService;
import com.yubin.apsfc.service.UserService;
import com.yubin.apsfc.service.imp.OrderServiceImp;
import com.yubin.apsfc.service.imp.UserServiceImp;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class UserView {
    Scanner scanner=new Scanner(System.in);
    UserService userService=null;
    public void userLoginView(User user) {
        System.out.print("1.点菜   2.查看订单   3.充值    4.查看余额: ");
        int i=scanner.nextInt();
        switch (i){
            case 1:
                orderfood(user.getId());
                userLoginView(user);
                break;
            case 2:
                showOrders(user.getId());
                userLoginView(user);
                break;
            case 3:
                recharge(user.getId());
                userLoginView(user);
                break;
            case 4:
                showmoney(user.getId());
                userLoginView(user);
                break;
        }
    }

    private void showmoney(int id) {
        System.out.print("请输入要查看余额的id: ");
        id=scanner.nextInt();
        userService=new UserServiceImp();
        int i = userService.showSumMoney(id);
        System.out.println("余额为: "+i);

    }

    private void recharge(int id) {
        System.out.print("请输入要充值的id: ");
        id=scanner.nextInt();
        System.out.print("请输入要充值的金额: ");
        int money=scanner.nextInt();
        userService=new UserServiceImp();
        int rechar = userService.rechar(id, money);
        if (rechar<=0){
            System.out.println("充值失败");
        }else {
            System.out.println("充值成功");
        }
    }

    private void orderfood(int userid) {
        OrderService orderService = new OrderServiceImp();
        ArrayList<Menus> menus = orderService.showMenus();
        System.out.println("菜品id    菜品名称   菜品类别   菜品原料   菜品简介   原价    会员价   ");
        for (Menus  menu:menus){
            System.out.println(menu.getMenuid()+" "+menu.getMenuName()+"  "+menu.getTypename()+menu.getBurden()+"  "+menu.getBrief()+"  "+menu.getPrice()+"  "+menu.getPricel());
        }

        //要根据会员等级  获取菜品的价格



        System.out.print("请输入要点的菜品id: ");
        int foodId = scanner.nextInt();
        System.out.print("请输入要点的菜品数量: ");
        int foodNum = scanner.nextInt();
        //点餐时间
        Date date =new Date();
        String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
        userService = new UserServiceImp();
        String grade = userService.selectGradeById(userid);
        //结算
        int sumMoney =0;//购买的总金额
        int yue = userService.selectUserMoneyById(userid);//根据id查用户余额
        if(grade!=null) {
            if (grade.equals("会员")) {
                int price1 = orderService.selectPriceOneByFoodId(foodId);
                sumMoney = orderService.countSumMoney(price1, foodNum);
                if (yue > sumMoney) {
                    int i1 = orderService.updateUserMoneyByIdAndAddOrder(userid, foodId, foodNum, format, sumMoney);
                    if (i1 == 2) {
                        System.out.println("购买成功");
                    }

                } else {
                    System.out.println("余额不足  不能购买");
                }

            } else if (grade.equals("用户")) {
                sumMoney = orderService.selectPriceByFoodId(foodId);
                if (yue > sumMoney) {
                    int i1 = orderService.updateUserMoneyByIdAndAddOrder(userid, foodId, foodNum, format, sumMoney);
                    if (i1 == 2) {
                        System.out.println("购买成功");
                    }

                } else {
                    System.out.println("余额不足  不能购买");
                }

            }
        }else{
            System.out.println("用户级别为空  请联系管理员操作");
        }




    }



    public void showOrders(int id) {
        userService = new UserServiceImp();
        ArrayList<Order> orders = userService.showOrders(id);
        System.out.println("用户名   菜品名称    购买数量     购买时间   ");
        for (Order ord:orders){
            System.out.println(ord.getUsername()+"  "+ord.getFooName()+  "   "+ ord.getFoodNum()+"  "+ord.getFoodTime());
        }
    }
}
