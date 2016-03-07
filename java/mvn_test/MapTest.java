package mvn_test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author hzweizijun 
 * @date 2015年9月15日 下午1:43:27
 */
public class MapTest {
	public static void main(String[] args) {
		Map<Long, String> testMap = new HashMap<Long, String>();
		testMap.put(1L, "value");
		testMap.put(2L, "value");
		testMap.put(3L, "value");
		testMap.put(4L, "value");
		
		Iterator<Long> iter = testMap.keySet().iterator();
		while (iter.hasNext()) {
			Long id = iter.next();
			String value = testMap.get(id);
			if (value != null) {
				testMap.remove(id);
			}
		}
	}
}
