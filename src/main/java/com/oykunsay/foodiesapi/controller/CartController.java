package com.oykunsay.foodiesapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.oykunsay.foodiesapi.io.CartRequest;
import com.oykunsay.foodiesapi.io.CartResponse;
import com.oykunsay.foodiesapi.service.CartService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/cart")
@AllArgsConstructor
public class CartController {

	private final CartService cartService;

	@PostMapping
	public CartResponse addToCart(@RequestBody CartRequest request) {
		String foodId = request.getFoodId();
		if (foodId == null || foodId.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "foodId not found");
		}
		return cartService.addToCart(request);
	}

}
