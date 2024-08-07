package com.chensoul.activemq.topic;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
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
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
			ActiveMQConnectionFactory.DEFAULT_USER,
			ActiveMQConnectionFactory.DEFAULT_PASSWORD,
			url
		);
		Connection connection = connectionFactory.createConnection();
		connection.start();

		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Destination destination = session.createTopic(topicName);
		MessageConsumer consumer = session.createConsumer(destination);

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

//		connection.close();
	}
}
