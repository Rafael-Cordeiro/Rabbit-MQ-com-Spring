package com.temperosoft.estoquepreco.rabbitmq;

import javax.annotation.PostConstruct;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.stereotype.Component;

import constants.RabbitMQConsts;

@Component
public class RabbitMQConnection {
	
	private static final String EXCHANGE_NAME = "amq.direct";
	private AmqpAdmin amqpAdmin;
	
	public RabbitMQConnection(AmqpAdmin amqpAdmin) {
		this.amqpAdmin = amqpAdmin;
	}

	private Queue queue(String name) {
		return new Queue(name, true, false, false);
	}
	
	private DirectExchange directExchange() {
		return new DirectExchange(EXCHANGE_NAME);
	}
	
	private Binding binding(Queue queue, DirectExchange exchange) {
		return new Binding(queue.getName(), Binding.DestinationType.QUEUE, exchange.getName(), queue.getName(), null);
	}
	
	@PostConstruct
	private void add() {
		Queue queueEstoque = queue(RabbitMQConsts.QUEUE_ESTOQUE);
		Queue queuePreco = queue(RabbitMQConsts.QUEUE_PRECO);
		
		DirectExchange exchange = directExchange();
		
		Binding bindingEstoque = binding(queueEstoque, exchange);
		Binding bindingPreco = binding(queuePreco, exchange);
		
		amqpAdmin.declareQueue(queueEstoque);
		amqpAdmin.declareQueue(queuePreco);
		amqpAdmin.declareExchange(exchange);
		amqpAdmin.declareBinding(bindingEstoque);
		amqpAdmin.declareBinding(bindingPreco);
	}
}
