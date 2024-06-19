package com.chensoul.spring.jms.topic;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.apache.activemq.ActiveMQConnectionFactory;

public class TopicConsumer {

	private static final String url = "tcp://127.0.0.1:61616";
	private static final String topicName = "topic-test";

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
		Destination destination = session.createTopic(topicName);
		// 6.创建消费者
		MessageConsumer consumer = session.createConsumer(destination);

		// 7.创建一个监听器
		consumer.setMessageListener(new MessageListener() {
			public void onMessage(Message message) {
				TextMessage textMessage = (TextMessage) message;
				try {
					System.out.println("接收消息：" + textMessage.getText());
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}
		});

		// 8.关闭连接
		//connection.close();
	}
}
