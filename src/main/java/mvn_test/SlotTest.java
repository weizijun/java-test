package mvn_test;
/**
 * @author hzweizijun 
 * @date 2015年12月14日 下午4:56:58
 */
public class SlotTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		allocNodes();
	}
	
	private static void allocNodes() {
		System.err.println("#!/bin/bash\n");
		String[] nodeInfo = {
			"redis-cli -h 10.180.157.199 cluster addslots ",
			"redis-cli -h 10.180.157.200 cluster addslots ",
			"redis-cli -h 10.180.157.200 cluster addslots "
		};
		
		int node = nodeInfo.length;
		int slotNum = 16384;
		int step = slotNum / node;
		StringBuffer[] addnodes = new StringBuffer[node]; 
		int begin = 0;
		for (int i = 0; i < node; ++i) {
			addnodes[i] = new StringBuffer(nodeInfo[i]);
			while (begin < step * (i + 1)) {
				addnodes[i].append(begin);
				addnodes[i].append(" ");
				begin++;
			}
		}
		
		for (; begin < slotNum; ++begin) {
			addnodes[node - 1].append(begin);
			addnodes[node - 1].append(" ");
		}
		
		for (StringBuffer buffer : addnodes) {
			System.err.println(buffer);
		}
	}

}
