package mvn_test;
/**
 * @author hzweizijun 
 * @date 2015年11月25日 下午6:41:45
 */
public class LengthTest {

	public static void main(String[] args) {
		int memPerInstance = 2300;
		long data = (long)memPerInstance * 1024 * 1024;
		String value = String.valueOf(data);
		System.out.println(data);
		System.out.println(value);
		long lmax = Long.MAX_VALUE;
		System.out.println("long max="+lmax);
	}

}
