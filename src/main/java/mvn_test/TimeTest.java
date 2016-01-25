package mvn_test;

import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.TimeoutUtils;


/**
 * @author hzweizijun 
 * @date 2015年12月10日 上午11:28:29
 */
public class TimeTest {

	public static void main(String[] args) {
		long timeout = 365;
		TimeUnit unit = TimeUnit.DAYS;
		final long rawTimeout = TimeoutUtils.toSeconds(timeout, unit);
		System.out.println(rawTimeout);
		System.out.println(Integer.MAX_VALUE);
		if (rawTimeout > Integer.MAX_VALUE) {
			System.out.println("yes");
		} else {
			System.out.println("no");
		}
	}

}
