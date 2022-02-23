package com.temperosoft.consumidorestoque.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import constants.RabbitMQConsts;
import dto.EstoqueDTO;

@Component
public class EstoqueConsumer {
	
	@RabbitListener(queues=RabbitMQConsts.QUEUE_ESTOQUE, containerFactory="rabbitListenerContainerFactory")
	private void consume(String message) throws InterruptedException, JsonMappingException, JsonProcessingException {
		EstoqueDTO estoqueDTO = new ObjectMapper().readValue(message, EstoqueDTO.class);
		System.out.println(estoqueDTO.codigoProduto);
		System.out.println(estoqueDTO.quantidade);
		System.out.println("Payload: " + message);
		System.out.println("-----------------------------");
		Thread.sleep(5000);
	}
}
