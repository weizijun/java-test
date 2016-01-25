package zktest;

import java.util.Collection;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.transaction.CuratorTransactionResult;
import org.apache.curator.retry.ExponentialBackoffRetry;

public class TransactionExample {

    public static void main(String[] args) throws Exception {
		CuratorFramework client = createSimple("10.166.224.26:2181");
		client.start();

		transaction(client);
    }
    

	public static CuratorFramework createSimple(String connectString) {
		ExponentialBackoffRetry retryPolicy = new ExponentialBackoffRetry(1000, 3);

		return CuratorFrameworkFactory.newClient(connectString, retryPolicy);
	}    

    public static Collection<CuratorTransactionResult> transaction(CuratorFramework client) throws Exception {
        // this example shows how to use ZooKeeper's new transactions
        Collection<CuratorTransactionResult> results = client.inTransaction().create().forPath("/a/path", "some data".getBytes())
        		.and().create().forPath("/another/path", "a data".getBytes())
                .and().setData().forPath("/another/path", "other data".getBytes())
                //.and().delete().forPath("/yet/another/path")
                .and().commit(); // IMPORTANT!

        for (CuratorTransactionResult result : results) {
            System.out.println(result.getForPath() + " - " + result.getType());
        }
        return results;
    }

}