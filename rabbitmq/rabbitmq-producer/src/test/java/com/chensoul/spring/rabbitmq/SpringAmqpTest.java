package com.chensoul.spring.rabbitmq;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SpringAmqpTest {
	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Test
	public void testSendMessage2SimpleQueue() {
		String queueName = "simple.queue";
		String message = "hello, spring amqp!";
		rabbitTemplate.convertAndSend(queueName, message);
	}

	@Test
	public void testSendMessage2WorkQueue() throws InterruptedException {
		String queueName = "simple.queue";
		String message = "hello, message__";
		for (int i = 1; i <= 50; i++) {
			rabbitTemplate.convertAndSend(queueName, message + i);
			Thread.sleep(20);
		}
	}

	@Test
	public void testSendFanoutExchange() {
		// 交换机名称
		String exchangeName = "itcast.fanout";
		// 消息
		String message = "hello, every one!";
		// 发送消息
		rabbitTemplate.convertAndSend(exchangeName, "", message);
	}

	@Test
	public void testSendDirectExchange() {
		// 交换机名称
		String exchangeName = "itcast.direct";
		// 消息
		String message = "hello, red!";
		// 发送消息
		rabbitTemplate.convertAndSend(exchangeName, "red", message);
	}

	@Test
	public void testSendTopicExchange() {
		// 交换机名称
		String exchangeName = "itcast.topic";
		// 消息
		String message = "今天天气不错，我的心情好极了!";
		// 发送消息
		rabbitTemplate.convertAndSend(exchangeName, "china.weather", message);
	}
}
