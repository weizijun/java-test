package curatortest;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderLatch;
import org.apache.curator.retry.ExponentialBackoffRetry;

public class MyLeaderLatchTest {
	private static final String PATH = "/examples/leader";
	private static LeaderLatch example;

	public static void main(String[] args) throws Exception {
		CuratorFramework client = createSimple("10.166.224.26:2181");
		example = new LeaderLatch(client, PATH, "Client " + args[0]);
		
		client.start();
		example.start();
		
		boolean isStop = false;
		while (true) {
			if (example.hasLeadership()) {
				System.out.println("current leader is mine");
				example.close();
				isStop = true;
				example = new LeaderLatch(client, PATH, "Client " + args[0]);
			} else {
				System.out.println("current leader is:" + example.getLeader().getId());
			}
			
			Thread.sleep(10 * 1000);
			if (isStop) {
				example.start();
				isStop = false;
			}
		}
	}


	public static CuratorFramework createSimple(String connectString) {
		ExponentialBackoffRetry retryPolicy = new ExponentialBackoffRetry(1000, 3);

		return CuratorFrameworkFactory.newClient(connectString, retryPolicy);
	}
}
