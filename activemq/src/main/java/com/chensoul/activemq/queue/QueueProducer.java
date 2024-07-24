package com.chensoul.activemq.queue;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.apache.activemq.ActiveMQConnectionFactory;

public class QueueProducer {
	private static final String url = "tcp://192.168.1.66:61616";
	private static final String queueName = "queue-test";

	public static void main(String[] args) throws JMSException {
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
			"admin",
			"admin123",
			url
		);

		Connection connection = connectionFactory.createConnection();
		connection.start();

		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Destination destination = session.createQueue(queueName);
		MessageProducer producer = session.createProducer(destination);

		for (int i = 0; i < 10; i++) {
			TextMessage textMessage = session.createTextMessage("textMessage" + i);
			producer.send(textMessage);
			System.out.println("发送消息：" + textMessage.getText());
		}
		connection.close();
	}
}
