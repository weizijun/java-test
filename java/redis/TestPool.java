package redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author hzweizijun 
 * @date 2015年8月13日 下午2:46:33
 */

class PoolThread extends Thread {
	private JedisPool pool;
	
	public PoolThread(JedisPool pool) {
		this.pool = pool;
	}
	
	@SuppressWarnings("deprecation")
	public void run() {
		Jedis client = pool.getResource();//从pool中获取资源
		try{
			client.select(0);
			client.set("k1", "v1");
			System.out.println(client.get("k1"));
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			//pool.returnResource(client);//向连接池“归还”资源，千万不要忘记。
			pool.returnBrokenResource(client);
		}
	}
}


public class TestPool {
	public static void main(String[] args) throws InterruptedException {
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(2);
		config.setMaxIdle(2);
		config.setMinIdle(0);
		config.setTestOnBorrow(false);
		config.setSoftMinEvictableIdleTimeMillis(15000);
		config.setTimeBetweenEvictionRunsMillis(20000);
		config.setMinEvictableIdleTimeMillis(300000);
		config.setTestWhileIdle(true);

		JedisPool pool = new JedisPool(config,"10.165.127.23",6380,15000,"123456",0);

		for (int i = 0; i < 1; i++) {
			PoolThread thread = new PoolThread(pool);
			thread.start();
		}
		
		Thread.sleep(10000);

	}
}