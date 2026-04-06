package com.oykunsay.foodiesapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.oykunsay.foodiesapi.entity.OrderEntity;

@Repository
public interface OrderRepository {
	List<OrderEntity> findByUserId(String userId);

	Optional<OrderEntity> findByRazorpayOrderId(String razorpayOrderId);
}
