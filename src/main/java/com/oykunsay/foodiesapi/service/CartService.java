package com.oykunsay.foodiesapi.service;

import com.oykunsay.foodiesapi.io.CartRequest;
import com.oykunsay.foodiesapi.io.CartResponse;

public interface CartService {
	CartResponse addToCart(CartRequest request);

	CartResponse getCart();

	void clearCart();

	CartResponse removeFromCart(CartRequest cartRequest);
}
