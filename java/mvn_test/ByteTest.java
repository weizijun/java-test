package mvn_test;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.nio.Buffer;
import java.nio.ByteBuffer;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;

/**
* @author hzweizijun
* @date 2016年3月2日 下午4:10:43
* 
*/
public class ByteTest {

	public static void main(String[] args) {
		byte[] data = {0x00,0x00,0x18,(byte) 0xEB,0x0A,(byte) 0xB4,(byte) 0xC2,0x60};
		ChannelBuffer bb = ChannelBuffers.wrappedBuffer(data);
		
		short status = bb.readShort();
		short port = bb.readShort();
		
		int id = bb.readInt();
		System.out.println(id);
		
		String host = ipIntegerToString(id);
		System.out.println(host);
		int ii = ipStringToInt(host);
		System.out.println(ii);
		
		ByteBuffer byteBuffer = ByteBuffer.allocate(8);
		IPPort ipPort = new IPPort(host, port);
		ipPort.serialize(byteBuffer);
		

		byte[] data2 = new byte[8]; 
		byteBuffer.flip();
		byteBuffer.get(data2);
		System.out.println(data2);
		
		byteBuffer.flip();
		IPPort ipPort2 = IPPort.deserialize(byteBuffer);
		System.out.println(ipPort2);
	}

	public static String ipIntegerToString(int id) {
		StringBuffer host = new StringBuffer(30);
		host.append((id & 0xff000000) >> 24).append('.');
		host.append((id & 0xff0000) >> 16).append('.');
		host.append((id & 0xff00) >> 8).append('.');
		host.append(id & 0xff);
		
		return host.toString();
	}
	
	public static int ipStringToInt(String ip) {
		int id = 0;
		String[] ipArr = ip.split("\\.");
		for (int i = 0; i < ipArr.length; ++i) {
			id += Integer.valueOf(ipArr[i]);
			if (i < ipArr.length - 1) {
				id *= 0x100;
			}
		}

		return id;
	}	
}




class IPPort implements Comparable<IPPort> {
	private String name;	// for sort
	private String ip;
	private int port;

	public IPPort(String ip, int port) {
		this.ip = ip;
		this.port = port;
	}

	public IPPort() {
		ip = "";
		port = 0;
	}

	@Override
	public boolean equals(Object obj) {
		IPPort obj1 = (IPPort) obj;
		return this.getIp().equals(obj1.getIp()) == true
				&& this.getPort() == obj1.getPort();
	}

	@Override
	public String toString() {
		return ip + ":" + port;
	}

	public static int size() {
		return 8;
	}

	public void serialize(ByteBuffer byteBuffer) {
		byteBuffer.putInt(port);
		if (null != ip) {
			byteBuffer.putInt(ByteTest.ipStringToInt(ip));
		} else {
			byteBuffer.putInt(0);
		}

	}

	public String getIp() {
		return ip;
	}
	
	public byte[] getByteIp() {
		return ip.getBytes();
	}

	public int getPort() {
		return port;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static IPPort deserialize(ByteBuffer byteBuffer) {

		int port = byteBuffer.getInt();
		int ip = byteBuffer.getInt();
		
		String host = ByteTest.ipIntegerToString(ip);
		return new IPPort(host, port);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(IPPort o) {
		if (this.getIp().equals(o.getIp()) == true) {
			return this.getPort() - o.getPort();
		} else {
			return this.getIp().compareTo(o.getIp());
		}
		
//		return name.compareTo(o.getName());
	}
}