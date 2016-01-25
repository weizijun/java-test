package mvn_test;

import org.zeromq.ZMQ;

/**
 * @author hzweizijun 
 * @date 2015年10月6日 下午6:33:05
 */

class SubThread extends Thread {
	public void run() {
		// TODO Auto-generated method stub
		ZMQ.Context context = ZMQ.context(1);  //创建一个I/O线程的上下文
		ZMQ.Socket socket = context.socket(ZMQ.SUB);   //创建一个request类型的socket，这里可以将其简单的理解为客户端，用于向response端发送数据
		
		socket.connect("tcp://127.0.0.1:5555");   //与response端建立连接
		socket.subscribe("".getBytes());
		long now = System.currentTimeMillis();
		
		for (int i = 0; i < 100000; i++) {
			byte[] response = socket.recv();   //接收response发送回来的数据  正在request/response模型中，send之后必须要recv之后才能继续send，这可能是为了保证整个request/response的流程走完
			System.out.println(Thread.currentThread() + " receive : " + new String(response));
		}
		long after = System.currentTimeMillis();
		
		System.out.println((after - now) / 1000);
	}
}

public class Sub {
	public static void main(String args[]) {
		for (int j = 0;  j < 5; j++) {
			Thread thread = new SubThread();
			thread.start();
		}
		
	}
}
