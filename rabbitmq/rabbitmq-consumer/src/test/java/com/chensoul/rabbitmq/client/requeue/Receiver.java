package com.chensoul.rabbitmq.client.requeue;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.QueueingConsumer.Delivery;

public class Receiver {

	public static void main(String[] args) throws Exception {


        ConnectionFactory connectionFactory = new ConnectionFactory() ;

        connectionFactory.setHost("localhost");
        connectionFactory.setPort(5672);
		connectionFactory.setVirtualHost("/");
		connectionFactory.setUsername("admin");
		connectionFactory.setPassword("123456");

        connectionFactory.setAutomaticRecoveryEnabled(true);
        connectionFactory.setNetworkRecoveryInterval(3000);
        Connection connection = connectionFactory.newConnection();

        Channel channel = connection.createChannel();

        String queueName = "test001";
        //durable 是否持久化消息
        channel.queueDeclare(queueName, true, false, false, null);
        QueueingConsumer consumer = new QueueingConsumer(channel);

        //	参数：队列名称、是否自动ACK、Consumer
        channel.basicConsume(queueName, false, consumer);
        //	循环获取消息
        while(true){
            //	获取消息，如果没有消息，这一步将会一直阻塞
            Delivery delivery = consumer.nextDelivery();
            String msg = new String(delivery.getBody());
            System.out.println("收到消息：" + msg);
            Thread.sleep(1000);

            if((Integer)delivery.getProperties().getHeaders().get("flag") == 0) {
            	//throw new RuntimeException("异常");
            	channel.basicNack(delivery.getEnvelope().getDeliveryTag(), false, false);
            } else {
            	channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
            }
        }
	}
}
