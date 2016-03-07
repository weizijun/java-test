package mvn_test;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author hzweizijun 
 * @date 2015年10月10日 上午9:49:23
 */
public class SetTest {
	public static void main(String[] args) {
		Set<String> hashSet = new HashSet<String>();
		hashSet.add("private");
		hashSet.add("idc");
		hashSet.add("public");
		System.out.println("hashSet:");
		for (String key : hashSet) {
			System.out.println(key);
		}
		
		System.out.println("");
		
		Set<String> linkedHashSet = new LinkedHashSet<String>();
		linkedHashSet.add("private");
		linkedHashSet.add("idc");
		linkedHashSet.add("public");
		System.out.println("linkedHashSet:");
		for (String key : linkedHashSet) {
			System.out.println(key);
		}
		
		
		System.out.println("");
		
		Set<String> treeSet = new TreeSet<String>();
		treeSet.add("private");
		treeSet.add("idc");
		treeSet.add("public");
		System.out.println("treeSet:");
		for (String key : treeSet) {
			System.out.println(key);
		}
	}
}
