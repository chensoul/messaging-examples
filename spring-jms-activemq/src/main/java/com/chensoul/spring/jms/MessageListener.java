package com.chensoul.spring.jms;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {
	@JmsListener(destination = "my-destination")
	public void onMessageReceived(String message) {
		System.out.println("Received message: " + message);
	}

	@JmsListener(destination = "mailbox", containerFactory = "myFactory")
	public void receiveMessage(Email email) {
		System.out.println("Received <" + email + ">");
	}
}
