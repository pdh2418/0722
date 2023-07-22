package com.internetdemo.tcpdemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer {
    public static void main(String[] args) throws IOException {
        //创建客户端的socket对象
        ServerSocket serverSocket = new ServerSocket(10002);
        //倾听这个套接字对象
        Socket accept = serverSocket.accept();
        //获取数据 输入流 读数据
        InputStream inputStream = accept.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String str;
        while ((str= bufferedReader.readLine())!=null){
            System.out.println(str);
        }
        serverSocket.close();
    }
}
