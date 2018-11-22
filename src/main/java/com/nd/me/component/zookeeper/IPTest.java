package com.nd.me.component.zookeeper;

import java.net.InetAddress;

/**
 * @Author
 * @Description
 * @Date Create in 上午 8:33 2018/6/12 0012
 */
public class IPTest {
    public static void main(String[] args) throws Exception {
        InetAddress addr = InetAddress.getLocalHost();
        String ip=addr.getHostAddress().toString(); //获取本机ip
        String hostName=addr.getHostName().toString(); //获取本机计算机名称
        System.out.println(ip);
        System.out.println(hostName);
    }
}
