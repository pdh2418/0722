package com.internetdemo.tcpdemo;

import java.io.*;
import java.net.Socket;

public class TcpClient {
    public static void main(String[] args) throws IOException {
        //创建客户端的socket对象 流的形式发送数据
        Socket socket = new Socket("127.0.0.1", 10002);
        //获取输入的数据   将输入的数据转换成字符流
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        //将socket转换成字符流写出
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        String str;
        while ((str=bufferedReader.readLine())!=null){
            if ("n".equals(str)){
                break;
            }
            bufferedWriter.write(str);
            bufferedWriter.write("\n");
            bufferedWriter.flush();

        }


        socket.close();
    }
}
