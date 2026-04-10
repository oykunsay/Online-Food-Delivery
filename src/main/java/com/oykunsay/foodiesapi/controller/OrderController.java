package com.oykunsay.foodiesapi.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oykunsay.foodiesapi.io.OrderRequest;
import com.oykunsay.foodiesapi.io.OrderResponse;
import com.oykunsay.foodiesapi.service.OrderService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/orders")
@AllArgsConstructor
public class OrderController {

	private final OrderService orderService;

	@PostMapping("/create")
	public OrderResponse createOrderWithPayment(@RequestBody OrderRequest request) {
		OrderResponse response = orderService.createOrderWithPayment(request);
		return response;
	}

}
