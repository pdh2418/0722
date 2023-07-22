package com.yubin.apsfc.view;

import com.yubin.apsfc.dean.Admin;
import com.yubin.apsfc.dean.User;
import com.yubin.apsfc.service.AdminService;
import com.yubin.apsfc.service.UserService;
import com.yubin.apsfc.service.imp.AdminServiceImp;
import com.yubin.apsfc.service.imp.UserServiceImp;
import com.yubin.apsfc.util.MailUtil;

import javax.mail.MessagingException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class MenusView {
    Scanner scanner=new Scanner(System.in);
    UserService userService=null;
    AdminView adminView=new AdminView();
    public void  menus(){
        System.out.print("1.注册   2.登录   3.修改密码   请选择");
        int i=scanner.nextInt();
        switch (i){
            case 1:
                register();
                menus();
                break;
            case 2:
                login();
                menus();
                break;
            case 3:
                modifypasswor();
                menus();
                break;
        }
    }

    private void modifypasswor() {
        System.out.println("请输入要修改密码的手机号  给你注册的邮箱发送验证码");
        String phone = scanner.next();
        userService= new UserServiceImp();
        String email = userService.selectEmailByPhone(phone);
        if (email!=null){


            int random = (int)(Math.random()*999999+100000);
            String str="这是验证吗"+random;
            try {
                MailUtil.sendMail(email,str);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
            System.out.println("请输入验证码");
            String yanzhegnma = scanner.next();
            if (yanzhegnma.equals(String.valueOf(random))){
                System.out.println("请输入修改后的密码");
                String newpassword = scanner.next();
                System.out.println("请再次输入修改后的密码");
                String newtwopassword = scanner.next();
                if (newpassword.equals(newtwopassword)){
                    int i = userService.updateUserPassWord(newpassword, phone, email);
                    if (i<=0){
                        System.out.println("修改失败");
                    }else {
                        System.out.println("修改成功 ");
                    }
                }else {
                    System.out.println("两次输入的密码不一致");
                }
            }else {
                System.out.println("验证码有误");
            }
        }else {
            System.out.println("找不到邮箱");
        }
    }

    private void login() {
        System.out.print("请输入用户名(手机号): ");
        String next = scanner.next();
        System.out.print("请输入密码: ");
        String next1 = scanner.next();
        if (isPhoneNum(next)){
            //是手机号就调用用户的登录方法
            userService= new UserServiceImp();
            User user = userService.loginUser(next, next1);
            if (user!=null){
                UserView usersView = new UserView();
                usersView.userLoginView(user);
            }

        }else{
            //不是手机号调用管理员的登录方法
           adminView.adminLoginView();
        }

    }

    private void register() {
        System.out.println("请输入用户名");
        String username = scanner.next();
        System.out.println("请输入密码");
        String pwd = scanner.next();
        System.out.println("请输入性别");
        String sex = scanner.next();
        System.out.println("请输入地址");
        String address = scanner.next();

        System.out.println("请输入手机号");
        String phones = scanner.next();
        System.out.println("请输入邮箱");
        String email = scanner.next();
        User user=new User(username,pwd,sex,address,phones,email,"用户");
        if (isPhoneNum(phones)){
            userService=new UserServiceImp();
            int i = userService.addUser(user);
            if (i<=0){
                System.out.println("注册失败");
            }else {
                System.out.println("注册成功");
            }
        }else {
            System.out.println("手机号有误");
        }
    }
    public boolean isPhoneNum(String phoneNum){

        //手机号码的正则表达式
        String regex="^1[3456789]\\d{9}$";

        return Pattern.matches(regex, phoneNum);
    }
}
