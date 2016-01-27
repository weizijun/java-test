package curatortest;

import java.util.Collection;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.transaction.CuratorTransactionResult;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.test.TestingServer;

public class TransactionExample {

    private static TestingServer server;

	public static void main(String[] args) throws Exception {
    	server = new TestingServer();
		CuratorFramework client = createSimple(server.getConnectString());
		client.start();

		transaction(client);
    }
    

	public static CuratorFramework createSimple(String connectString) {
		ExponentialBackoffRetry retryPolicy = new ExponentialBackoffRetry(1000, 3);

		return CuratorFrameworkFactory.newClient(connectString, retryPolicy);
	}    

    public static Collection<CuratorTransactionResult> transaction(CuratorFramework client) throws Exception {
        // this example shows how to use ZooKeeper's new transactions
        Collection<CuratorTransactionResult> results = client.inTransaction().create().forPath("/path", "some data".getBytes())
        		.and().create().forPath("/another", "a data".getBytes())
                .and().setData().forPath("/another", "other data".getBytes())
                //.and().delete().forPath("/yet/another/path")
                .and().commit(); // IMPORTANT!

        for (CuratorTransactionResult result : results) {
            System.out.println(result.getForPath() + " - " + result.getType());
        }
        return results;
    }

}