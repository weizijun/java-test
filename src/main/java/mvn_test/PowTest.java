package mvn_test;
/**
 * @author hzweizijun 
 * @date 2015年11月18日 下午2:33:59
 */
public class PowTest {

	public static void main(String[] args) {
		long base = 30000;
		int count = 0;
		for (; count < 10; count++) {
			long unlockDelay = base * (long) Math.pow(2, count);
			System.out.println(unlockDelay/1000);
		}
		
	}

}
