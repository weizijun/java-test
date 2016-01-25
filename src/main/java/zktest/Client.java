package zktest;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooDefs.Ids;

/**
 * @author hzweizijun 
 * @date 2016年1月23日 下午8:42:50
 */
public class Client implements Watcher {
	private static CountDownLatch connectedSemaphore = new CountDownLatch(1);
	
	public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
		ZooKeeper zooKeeper = new ZooKeeper("127.0.0.1:2181", 5000,
				new Client());
		
		System.out.println(zooKeeper.getState());
		
		connectedSemaphore.await();
		System.out.println("established");
		System.out.println(zooKeeper.getState());
		
		String path1 = zooKeeper.create("/zktest", "hello world!".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
		System.out.println("create znode:" + path1);
		
		String path2 = zooKeeper.create("/zktest", "hello zk".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
		System.out.println("create znode:" + path2);
	}

	@Override
	public void process(WatchedEvent event) {
		System.out.println("event:" + event);
		if (KeeperState.SyncConnected == event.getState()) {
			connectedSemaphore.countDown();
		}
	}

}
