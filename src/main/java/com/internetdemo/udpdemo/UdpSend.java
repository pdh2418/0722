package com.internetdemo.udpdemo;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UdpSend {
    public static void main(String[] args) throws Exception {
        //创建socket对象
        DatagramSocket datagramSocket = new DatagramSocket();
        //创建要发送的数据
        byte[] bytes = "I miss you".getBytes();
        DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length, InetAddress.getByName(""), 10086);
        //调用发送数据的方法
        datagramSocket.send(datagramPacket);
        datagramSocket.close();
    }
}
