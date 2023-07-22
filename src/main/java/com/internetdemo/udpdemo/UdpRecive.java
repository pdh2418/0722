package com.internetdemo.udpdemo;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UdpRecive {
    public static void main(String[] args) throws Exception {
        //创建socket对象
        DatagramSocket datagramSocket = new DatagramSocket(10086);
        //接受数据
        byte[]  bytes=new byte[1022];
        DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length);
        datagramSocket.receive(datagramPacket);
        System.out.println("数据是："+new String(datagramPacket.getData(),0, datagramPacket.getLength()));
    }
}
