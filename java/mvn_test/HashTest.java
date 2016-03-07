package mvn_test;

import redis.clients.util.JedisClusterCRC16;


/**
 * @author hzweizijun 
 * @date 2015年12月22日 下午2:57:01
 */
public class HashTest {

	public static void main(String[] args) {
		for(int i = 0; i < 100000; ++i) {
			String key = "key" + i;
			int hashCode = JedisClusterCRC16.getSlot(key);
			System.out.println(key + ":" + hashCode);
		}
	}

}
