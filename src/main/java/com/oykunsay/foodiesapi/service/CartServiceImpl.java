package com.oykunsay.foodiesapi.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.oykunsay.foodiesapi.entity.CartEntity;
import com.oykunsay.foodiesapi.io.CartRequest;
import com.oykunsay.foodiesapi.io.CartResponse;
import com.oykunsay.foodiesapi.repository.CartRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CartServiceImpl implements CartService {

	private final CartRepository cartRepository;
	private final UserService userService;

	@Override
	public CartResponse addToCart(CartRequest request) {
		String loggedInUserId = userService.findByUserId();
		Optional<CartEntity> cartOptional = cartRepository.findByUserId(loggedInUserId);
		CartEntity cart = cartOptional.orElseGet(() -> new CartEntity(loggedInUserId, new HashMap<>()));

		Map<String, Integer> cartItems = cart.getItems();
		cartItems.put(request.getFoodId(), cartItems.getOrDefault(request.getFoodId(), 0) + 1);
		cart.setItems(cartItems);
		cart = cartRepository.save(cart);
		return convertToResponse(cart);
	}

	private CartResponse convertToResponse(CartEntity cartEntity) {
		return CartResponse.builder().id(cartEntity.getId()).userId(cartEntity.getUserId()).items(cartEntity.getItems())
				.build();
	}

	@Override
	public CartResponse getCart() {
		String loggedInUserId = userService.findByUserId();
		CartEntity entity = cartRepository.findByUserId(loggedInUserId)
				.orElse(new CartEntity(null, loggedInUserId, new HashMap<String, Integer>()));
		return convertToResponse(entity);
	}

}
