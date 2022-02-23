package com.temperosoft.estoquepreco.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RabbitMQService {
	
	@Autowired
	RabbitTemplate rabbitTemplate;
	
	@Autowired
	ObjectMapper objectMapper;
	
	public <T> void sendMessage(String queueName, T message) {
		try {
			String messageJson = objectMapper.writeValueAsString(message);
			rabbitTemplate.convertAndSend(queueName, messageJson);
			log.info("Message sent successfuly" +
					"\nMessage class: " + message.getClass().getCanonicalName() +
					"\nQueue: " + queueName +
					"\nPayload: " + messageJson
			);			
		} catch (JsonProcessingException e) {
			log.error("An error has occured", e);
		}
	}
	
	
}
