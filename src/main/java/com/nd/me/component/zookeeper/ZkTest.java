package com.nd.me.component.zookeeper;

import org.apache.zookeeper.*;

/**
 * @Author
 * @Description
 * @Date Create in 下午 5:07 2018/6/11 0011
 */
public class ZkTest {
    public static void main(String[] args) throws Exception{

        ZooKeeper zkClient = new ZooKeeper("127.0.0.1:2181", 3000, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                System.out.println("---------"+watchedEvent.getPath());
            }
        });

        //zkClient.create("/payService","mydata".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        String p1 = zkClient.create("/payService/"+System.currentTimeMillis()+"_","mydata".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT_SEQUENTIAL);
        System.out.println(p1);

        String p2 = zkClient.create("/payService/"+System.currentTimeMillis()+"_","mydata".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        System.out.println(p2);

        String p3 = zkClient.create("/payService/"+System.currentTimeMillis()+"_","mydata".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        System.out.println(p3);

        String[] ss = p3.split("_");
        long worker_id = Long.parseLong(ss[1]);
        System.out.println("worker_id:"+worker_id);
    }
}
