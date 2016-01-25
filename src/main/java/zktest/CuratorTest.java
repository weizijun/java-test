package zktest;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.CloseableUtils;
import org.apache.zookeeper.CreateMode;

public class CuratorTest {
	private static final String PATH = "/testzktemp/basic";

	public static void main(String[] args) throws Exception {
		String connectString = "10.166.224.26:2181";
		CuratorFramework client = createWithOptions(connectString, new ExponentialBackoffRetry(1000, 3), 1000, 1000);
		client.start();
		client.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath(PATH, "hello zk!".getBytes());
		CloseableUtils.closeQuietly(client);
		
		client = createWithOptions(connectString, new ExponentialBackoffRetry(1000, 3), 1000, 1000);
		client.start();
		
		System.out.println(new String(client.getData().forPath(PATH)));

	}
	
	public static CuratorFramework createSimple(String connectString) {
		ExponentialBackoffRetry retryPolicy = new ExponentialBackoffRetry(1000, 3);
		
		return CuratorFrameworkFactory.newClient(connectString, retryPolicy);
	}
	
	public static CuratorFramework createWithOptions(String connectString, RetryPolicy retryPolicy, int connectionTimeoutMs, int sessionTimeoutMs) {
		return CuratorFrameworkFactory.builder().connectString(connectString)
				.retryPolicy(retryPolicy)
				.connectionTimeoutMs(connectionTimeoutMs)
				.sessionTimeoutMs(sessionTimeoutMs)
				.namespace("wzj")
				.build();
	}

}
