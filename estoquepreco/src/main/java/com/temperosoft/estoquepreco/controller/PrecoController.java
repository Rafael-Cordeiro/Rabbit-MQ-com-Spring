package com.temperosoft.estoquepreco.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.temperosoft.estoquepreco.service.RabbitMQService;

import constants.RabbitMQConsts;
import dto.PrecoDTO;

@RestController
@RequestMapping("preco")
public class PrecoController {
	
	@Autowired
	private RabbitMQService rabbitMQService;
	
	@SuppressWarnings("rawtypes")
	@PutMapping
	private ResponseEntity alteraPreco(@RequestBody PrecoDTO precoDTO) {
		rabbitMQService.sendMessage(RabbitMQConsts.QUEUE_PRECO, precoDTO);
		return new ResponseEntity(HttpStatus.OK);
	}
}
