package com.chensoul.spring.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageSenderService {

	@Autowired
	private JmsTemplate jmsTemplate;

	public void sendMessage(String message) {
		jmsTemplate.convertAndSend(message);
	}

	public void sendEmail(Email email) {
		jmsTemplate.convertAndSend("mailbox", email);
	}
}
