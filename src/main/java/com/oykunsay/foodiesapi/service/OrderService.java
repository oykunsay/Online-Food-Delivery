package com.oykunsay.foodiesapi.service;

import com.oykunsay.foodiesapi.io.OrderRequest;
import com.oykunsay.foodiesapi.io.OrderResponse;

public interface OrderService {

	OrderResponse createOrderWithPayment(OrderRequest request);
}
