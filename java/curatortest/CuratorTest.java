package curatortest;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

public class CuratorTest {
	private static final String PATH = "/testzktemp/basic4";
	private static CuratorFramework client;

	public static void main(String[] args) throws Exception {
		String connectString = "10.180.148.7:2181,10.180.148.9:2181,10.180.148.15:2181";
		client = createWithOptions(connectString, new ExponentialBackoffRetry(1000, 3), 1000, 1000);
		client.start();
		
		//System.out.println(new String(client.getData().forPath(PATH)));
		// createPath(PATH);
		// createPath(PATH);
		setPathData(PATH, "test1");
		setPathData(PATH, "test2");
		setPathData(PATH, "test3");
		deletePath(PATH);
		deletePath(PATH);

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
	
	public static void createPath(String path) throws Exception {
		if (client.checkExists().forPath(path) == null) {
			client.create().creatingParentsIfNeeded().forPath(path);
		}
	}
	
	public static void setPathData(String path, String data) throws Exception {
		if (client.checkExists().forPath(path) != null) {
			client.setData().forPath(path, data.getBytes());
		} else {
			client.create().creatingParentsIfNeeded().forPath(path, data.getBytes());
		}
	}
	
	public static void deletePath(String path) throws Exception {
		if (client.checkExists().forPath(path) != null) {
			client.delete().deletingChildrenIfNeeded().forPath(path);	
		}
	}

}
