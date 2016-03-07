package mvn_test;

import org.zeromq.ZMQ;

/**
 * @author hzweizijun 
 * @date 2015年10月6日 下午6:32:59
 */
public class Pub {
	public static void main (String[] args) {
		ZMQ.Context context = ZMQ.context(5);  //这个表示创建用于一个I/O线程的context
		
		ZMQ.Socket socket = context.socket(ZMQ.PUB);  //创建一个response类型的socket，他可以接收request发送过来的请求，其实可以将其简单的理解为服务端
		socket.bind ("tcp://*:5555");    //绑定端口
		int i = 0;
		int number = 0;
		while (!Thread.currentThread().isInterrupted()) {
			i++;
			if (i == 10000) {
				i = 0;
				System.out.println(++number);
			}

			String response = "hello world";
			socket.send(response.getBytes());  //向request端发送数据  ，必须要要request端返回数据，没有返回就又recv，将会出错，这里可以理解为强制要求走完整个request/response流程
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		socket.close();  //先关闭socket
		context.term();  //关闭当前的上下文
        
       
    }
}
