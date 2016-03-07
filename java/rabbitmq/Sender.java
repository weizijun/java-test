package rabbitmq;

import java.io.IOException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @author hzweizijun
 * @date 2016年3月7日 下午4:38:52
 * 
 */
public class Sender {
	
	private final static String QUEUE_NAME = "hello";

	public static void main(String[] args) throws IOException {
		ConnectionFactory factory = new ConnectionFactory();

		factory.setHost("10.166.224.29");

		Connection connection = factory.newConnection();

		Channel channel = connection.createChannel();
		
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
	    String message = "Hello World!";
	    channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
	    System.out.println(" [x] Sent '" + message + "'");
	    
	    channel.close();
	    connection.close();
	}

}