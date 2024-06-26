package com.chensoul.rabbitmq.client.exchange.direct;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Sender4DirectExchange {


	public static void main(String[] args) throws Exception {

		//1 创建ConnectionFactory
		ConnectionFactory connectionFactory = new ConnectionFactory();
		connectionFactory.setHost("localhost");
		connectionFactory.setPort(5672);
		connectionFactory.setVirtualHost("/");
		connectionFactory.setUsername("admin");
		connectionFactory.setPassword("123456");

		//2 创建Connection
		Connection connection = connectionFactory.newConnection();
		//3 创建Channel
		Channel channel = connection.createChannel();
		//4 声明
		String exchangeName = "test_direct_exchange";
		String routingKey = "test_direct_routingKey";
		//5 发送

		String msg = "Hello World RabbitMQ 4  Direct Exchange Message ... ";
		channel.basicPublish(exchangeName, routingKey , null , msg.getBytes());

	}

}
