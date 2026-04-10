package com.oykunsay.foodiesapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.oykunsay.foodiesapi.entity.OrderEntity;

@Repository
public interface OrderRepository extends MongoRepository<OrderEntity, String> {

	List<OrderEntity> findByUserId(String userId);

	Optional<OrderEntity> findByPaymentToken(String paymentToken);
}