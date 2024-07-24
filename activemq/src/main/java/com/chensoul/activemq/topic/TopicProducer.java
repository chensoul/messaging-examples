package com.chensoul.activemq.topic;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.apache.activemq.ActiveMQConnectionFactory;

public class TopicProducer {
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
		MessageProducer producer = session.createProducer(destination);

		for (int i = 0; i < 10; i++) {
			TextMessage textMessage = session.createTextMessage("textMessage" + i);
			producer.send(textMessage);
			System.out.println("发送消息：" + textMessage.getText());
		}

		connection.close();
	}
}
