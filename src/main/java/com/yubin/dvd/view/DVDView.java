package com.yubin.dvd.view;

import com.yubin.dvd.bean.Dvd;
import com.yubin.dvd.service.DvdService;

import java.util.List;
import java.util.Scanner;

public class DVDView {
    Scanner scanner=new Scanner(System.in);
    DvdService dvdService=new DvdService();
    public void menu() {
        System.out.println("dvd系统  1.添加  2.查看  3.删除  4.修改  请选择: ");
        int i=scanner.nextInt();
        switch (i){
            case 1:
                addDVD();
                menu();
                break;
            case 2:
                showDVD();
                menu();
                break;
            case 3:
                dropDvd();
                menu();
                break;
            case 4:
                modify();
                menu();
                break;
        }
    }

    private void modify() {
        System.out.print("修改id: ");
        int id=scanner.nextInt();
        dvdService.modifyDvd(id);

    }

    private void dropDvd() {
        System.out.print("请输入要删除dvd的id: ");
        int dvdId=scanner.nextInt();
        dvdService.drop(dvdId);
    }

    private void showDVD() {
        List<Dvd> dvds=dvdService.showDvd();
        System.out.println("id     name     state    date");
        for (int i = 0; i < dvds.size(); i++) {
            System.out.println(dvds.get(i).getId()+"      "+dvds.get(i).getName()+
                    "      "+dvds.get(i).getState()+"      "+dvds.get(i).getDate());
        }
    }

    private void addDVD() {
        System.out.print("请输入DVD id: ");
        int id = scanner.nextInt();
        System.out.print("请输入dvd 名称: ");
        String dvdname = scanner.next();
        System.out.print("请输入日期: ");
        String date = scanner.next();
        Dvd d = new Dvd(id, dvdname, "可借", date);
        int add = dvdService.add(d);
        if (add<=0){
            System.out.println("添加失败");
        }else {
            System.out.println("添加成功");
        }
    }
}
