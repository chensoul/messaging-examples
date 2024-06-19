package com.chensoul.spring.jms.queue;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.apache.activemq.ActiveMQConnectionFactory;

public class QueueProducer {

	private static final String url = "tcp://127.0.0.1:61616";
	private static final String queueName = "queue-test";

	public static void main(String[] args) throws JMSException {
		// 1.创建ConnectionFactory
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
		// 2.创建Connection
		Connection connection = connectionFactory.createConnection();
		// 3.启动连接
		connection.start();

		// 4.创建会话,false，不使用事务，自动应答模式
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		// 5.创建一个目标
		Destination destination = session.createQueue(queueName);
		// 6.创建生产者
		MessageProducer producer = session.createProducer(destination);

		// 7.创建消息并发送
		for (int i = 0; i < 10; i++) {
			// 创建消息
			TextMessage textMessage = session.createTextMessage("textMessage" + i);
			// 发布消息
			producer.send(textMessage);
			System.out.println("发送消息：" + textMessage.getText());
		}

		// 8.关闭连接
		connection.close();

	}
}
