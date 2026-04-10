package com.oykunsay.foodiesapi.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.iyzipay.model.CheckoutFormInitialize;
import com.oykunsay.foodiesapi.entity.OrderEntity;
import com.oykunsay.foodiesapi.io.OrderRequest;
import com.oykunsay.foodiesapi.io.OrderResponse;
import com.oykunsay.foodiesapi.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

	private final OrderRepository orderRepository;
	private final IyzicoService iyzicoService;

	@Override
	@Transactional
	public OrderResponse createOrderWithPayment(OrderRequest request) {
		OrderEntity newOrder = convertToEntity(request);
		newOrder.setPaymentStatus("PENDING");
		newOrder.setOrderStatus("WAITING_PAYMENT");
		newOrder = orderRepository.save(newOrder);

		String formHtml = null;

		try {
			System.out.println("DEBUG: Iyzico is starting...");
			CheckoutFormInitialize checkoutForm = iyzicoService.prepareCheckoutForm(newOrder, request);

			if (checkoutForm == null) {
				System.out.println("DEBUG: CheckoutForm object is null");
			} else {
				System.out.println("DEBUG: Iyzico Status: " + checkoutForm.getStatus());

				if ("failure".equals(checkoutForm.getStatus())) {
					System.out.println("DEBUG: Iyzico Error Messge: " + checkoutForm.getErrorMessage());
				} else {
					System.out.println("DEBUG: Iyzico Token: " + checkoutForm.getToken());
					newOrder.setPaymentToken(checkoutForm.getToken());
					formHtml = checkoutForm.getCheckoutFormContent();
				}
			}
		} catch (Exception e) {
			System.out.println("DEBUG: Iyzico error!");
			e.printStackTrace();
		}
		newOrder = orderRepository.save(newOrder);
		return convertToResponse(newOrder, formHtml);
	}

	private OrderEntity convertToEntity(OrderRequest request) {
		return OrderEntity.builder().userId(request.getUserId()).userAddress(request.getUserAddress())
				.phoneNumber(request.getPhoneNumber()).email(request.getEmail()).amount(request.getAmount())
				.orderedItems(request.getOrderedItems()).build();
	}

	private OrderResponse convertToResponse(OrderEntity entity, String formHtml) {
		return OrderResponse.builder().id(entity.getId()).userId(entity.getUserId())
				.userAddress(entity.getUserAddress()).phoneNumber(entity.getPhoneNumber()).email(entity.getEmail())
				.amount(entity.getAmount()).paymentStatus(entity.getPaymentStatus())
				.orderStatus(entity.getOrderStatus()).paymentToken(entity.getPaymentToken()).paymentFormHtml(formHtml)
				.build();
	}
}