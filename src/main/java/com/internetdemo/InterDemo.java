package com.internetdemo;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InterDemo {
    public static void main(String[] args) throws UnknownHostException {
        //获取此域名下面的IP地址  或者域名
        InetAddress byName = InetAddress.getByName("www.baidu.com");
        String hostName = byName.getHostName();
        System.out.println(hostName);
        String hostAddress = byName.getHostAddress();
        System.out.println(hostAddress);
    }

}
