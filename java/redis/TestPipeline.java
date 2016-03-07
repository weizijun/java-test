package redis;

import java.util.List;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

public class TestPipeline {

	private static Jedis jedis;

	public static void main(String[] args) {
		jedis = new Jedis("10.180.157.100");
		jedis.auth("123456");
		Pipeline p = jedis.pipelined();
		p.get("key");
		p.get("key1");
		p.get("key2");
		p.get("key3");
		p.get("key4");
		p.get("key5");
		p.get("key6");
		List<Object> responses = p.syncAndReturnAll();  
		for(Object resp : responses){  
            System.out.println("Response:" + resp);//注意，此处resp的类型为Long  
        } 
	}

}
