package mvn_test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author hzweizijun 
 * @date 2015年10月27日 下午6:16:34
 */
public class PattenTest {

	public static void main(String[] args) {
		String slaveInfo = "ip=10.180.156.29,port=6380,state=online,offset=1107,lag=0";
		String ip = null;
		int port = 0;
		long slaveOffset = 0;
		
		String pattern = "^ip=([\\d|.]+),port=(\\d+),state=(\\w+),offset=(\\d+),lag=(\\d+)$";
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(slaveInfo);
		if (m.find()) {
			ip = m.group(1);
			port = Integer.parseInt(m.group(2));
			slaveOffset = Integer.parseInt(m.group(4));
		} 
		
		System.out.println(m.group());
	}

}
