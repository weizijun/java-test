package zktest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.data.Stat;

/**
 * @author hzweizijun 
 * @date 2016年1月23日 下午8:42:50
 */
public class Client implements Watcher {
	private static CountDownLatch connectedSemaphore = new CountDownLatch(1);
	private static String connectString = "10.180.148.7:2181,10.180.148.9:2181,10.180.148.15:2181";
	private static ZooKeeper zooKeeper;
	
	public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
		zooKeeper = new ZooKeeper(connectString, 5000,
				new Client());
		
		System.out.println(zooKeeper.getState());
		
		connectedSemaphore.await();
		System.out.println("established");
		System.out.println(zooKeeper.getState());
		
		String path1 = zooKeeper.create("/zktest", "hello world!".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
		System.out.println("create znode:" + path1);
		
		String path2 = zooKeeper.create("/zktest", "hello zk".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
		System.out.println("create znode:" + path2);
		
		Stat stat = new Stat();
		zooKeeper.getData("/zktest", true, stat);

		zooKeeper.setData("/zktest", "data".getBytes(), -1);
		
		
		zooKeeper.setData("/zktest", "data2".getBytes(), -1);
		zooKeeper.setData("/zktest", "data3".getBytes(), -1);
		zooKeeper.setData("/zktest", "data4".getBytes(), -1);
		
		System.out.println("Press enter/return to quit\n");
		new BufferedReader(new InputStreamReader(System.in)).readLine();
	}

	@Override
	public void process(WatchedEvent event) {
		System.out.println("event:" + event);
		if (KeeperState.SyncConnected == event.getState()) {
			connectedSemaphore.countDown();
		}
		
		zooKeeper.register(this);
	}

}
