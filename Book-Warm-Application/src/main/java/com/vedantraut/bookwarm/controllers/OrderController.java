package com.vedantraut.bookwarm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.vedantraut.bookwarm.dtos.OrderDTO;
import com.vedantraut.bookwarm.dtos.ResponseDTO;
import com.vedantraut.bookwarm.services.OrderService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
	
	@Autowired
	OrderService orderService;
	
	@PostMapping("/checkout")
	public ResponseEntity<ResponseDTO> placeOrder(@RequestBody @Valid OrderDTO orderdto) {
		
		ResponseDTO responsedto = orderService.placeOrder(orderdto);
		
		if(responsedto.getStatus().equalsIgnoreCase("SUCCESS")) {
			return ResponseEntity.ok(responsedto);
		} 
		else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responsedto);
		}
	}
	
}
