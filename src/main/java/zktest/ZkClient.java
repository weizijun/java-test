package zktest;

import java.io.IOException;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;

public class ZkClient {

	public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
		ZooKeeper zk = new ZooKeeper("10.166.224.26:2181", 
		        1000, new Watcher() { 
		            // 监控所有被触发的事件
		            public void process(WatchedEvent event) { 
		                System.out.println("已经触发了" + event.getType() + "事件！"); 
		            } 
		        }); 
		 // 创建一个目录节点
		 zk.create("/testzk", "hello zk!".getBytes(), Ids.OPEN_ACL_UNSAFE,
		   CreateMode.PERSISTENT); 
	}

}
