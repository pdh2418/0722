package com.internetdemo;

import java.net.MalformedURLException;
import java.net.URL;

public class UrlDemo {
    public static void main(String[] args) throws MalformedURLException {
        URL url = new URL("https://cn.bing.com/search?q=%E7%99%BE%E5%BA%A6&cvid=b74732f76c414d7a8ddb2cc401be7abe&aqs=edge..69i57j0l5j69i61l3.3311j0j9&FORM=ANAB01&PC=LCTS");
        String protocol = url.getProtocol();
        System.out.println("协议："+protocol);
        String host = url.getHost();
        System.out.println("域名："+host);
        System.out.println("端口："+url.getPort());
        System.out.println("请求资源："+url.getPath());
        System.out.println("请求资源："+url.getFile());
        System.out.println("参数："+url.getQuery());
    }
}
