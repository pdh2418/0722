package com.yubin.apsfc.view;

import com.yubin.apsfc.dean.Admin;
import com.yubin.apsfc.service.AdminService;
import com.yubin.apsfc.service.imp.AdminServiceImp;

import java.util.Scanner;

public class AdminView {
    AdminService adminService=null;
    Scanner scanner=new Scanner(System.in);
    public void adminLoginView() {
        System.out.println("1.增加     2.删除     3.修改    4.查看");
        System.out.print("请选择: ");
        int n=scanner.nextInt();
        switch (n){
            case 1:
                add();
                adminLoginView();
                break;
            case 2:
                drop();
                adminLoginView();
                break;
            case 3:
                xiugai();
                adminLoginView();
                break;
            case 4:
                show();
                adminLoginView();
                break;
        }
    }

    private void add() {
        System.out.print("请输入要添加的姓名: ");
        String name=scanner.next();
        System.out.print("请输入要添加的密码: ");
        String pwe=scanner.next();
        Admin admin = new Admin(name, pwe);
        adminService=new AdminServiceImp();
        int addmin = adminService.addmin(admin);
        if (addmin<=0) {
            System.out.println("添加失败");
        }else {
            System.out.println("添加成功");
        }
    }

    private void xiugai() {
        System.out.print("请输入要修改的id: ");
        int id=scanner.nextInt();
        adminService=new AdminServiceImp();
        boolean exist = adminService.exist(id);
        if (exist==true){
            int modif = adminService.modif(id);
            if (modif<=0){
                System.out.println("删除失败");
            }else {
                System.out.println("删除成功");
            }
        }else {
            System.out.println("找不到id   修改失败");
        }
    }

    private void drop() {
        System.out.print("请输入要删除的id: ");
        int id=scanner.nextInt();
        adminService=new AdminServiceImp();
        boolean exist = adminService.exist(id);
        if (exist==true){
            int delect = adminService.delect(id);
            if (delect<=0){
                System.out.println("删除失败");
            }else {
                System.out.println("删除成功");
            }
        }else {
            System.out.println("找不到该id 无法删除");
        }
    }

    private void show() {
        adminService=new AdminServiceImp();
        adminService.showmin();
    }
}
