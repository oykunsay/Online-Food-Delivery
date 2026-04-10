package com.oykunsay.foodiesapi.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iyzipay.Options;
import com.iyzipay.model.Address;
import com.iyzipay.model.BasketItem;
import com.iyzipay.model.BasketItemType;
import com.iyzipay.model.Buyer;
import com.iyzipay.model.CheckoutFormInitialize;
import com.iyzipay.model.Currency;
import com.iyzipay.model.Locale;
import com.iyzipay.model.PaymentGroup;
import com.iyzipay.request.CreateCheckoutFormInitializeRequest;
import com.oykunsay.foodiesapi.entity.OrderEntity;
import com.oykunsay.foodiesapi.io.OrderRequest;
import com.oykunsay.foodiesapi.io.OrderResponse;
import com.oykunsay.foodiesapi.repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private Options iyzicoOptions;

	@Override
	public OrderResponse createOrderWithPayment(OrderRequest request) {
		OrderEntity newOrder = convertToEntity(request);
		newOrder.setPaymentStatus("PENDING");
		newOrder.setOrderStatus("WAITING_PAYMENT");
		newOrder = orderRepository.save(newOrder);

		CheckoutFormInitialize checkoutForm = initializeIyzicoPayment(newOrder, request);

		newOrder.setPaymentToken(checkoutForm.getToken());
		orderRepository.save(newOrder);

		return OrderResponse.builder().id(newOrder.getId()).userId(newOrder.getUserId())
				.userAddress(newOrder.getUserAddress()).phoneNumber(newOrder.getPhoneNumber())
				.email(newOrder.getEmail()).amount(newOrder.getAmount()).paymentStatus(newOrder.getPaymentStatus())
				.orderStatus(newOrder.getOrderStatus()).paymentToken(newOrder.getPaymentToken())
				.paymentFormHtml(checkoutForm.getCheckoutFormContent()).build();
	}

	private CheckoutFormInitialize initializeIyzicoPayment(OrderEntity order, OrderRequest request) {
		CreateCheckoutFormInitializeRequest iyzicoRequest = new CreateCheckoutFormInitializeRequest();
		iyzicoRequest.setLocale(Locale.TR.getValue());
		iyzicoRequest.setConversationId(order.getId());
		iyzicoRequest.setPrice(new BigDecimal(order.getAmount()));
		iyzicoRequest.setPaidPrice(new BigDecimal(order.getAmount()));
		iyzicoRequest.setCurrency(Currency.TRY.name());
		iyzicoRequest.setBasketId(order.getId());
		iyzicoRequest.setPaymentGroup(PaymentGroup.PRODUCT.name());
		iyzicoRequest.setCallbackUrl("http://localhost:8080/api/payments/callback");

		Buyer buyer = new Buyer();
		buyer.setId(order.getUserId());
		buyer.setName(request.getFirstName());
		buyer.setSurname(request.getLastName());
		buyer.setGsmNumber(request.getPhoneNumber());
		buyer.setEmail(request.getEmail());
		buyer.setIdentityNumber("11111111111");
		buyer.setRegistrationAddress(order.getUserAddress());
		buyer.setCity("Trabzon");
		buyer.setCountry("Turkey");
		iyzicoRequest.setBuyer(buyer);

		Address address = new Address();
		address.setContactName(request.getFirstName() + " " + request.getLastName());
		address.setCity("Trabzon");
		address.setCountry("Turkey");
		address.setAddress(order.getUserAddress());
		iyzicoRequest.setShippingAddress(address);
		iyzicoRequest.setBillingAddress(address);

		List<BasketItem> basketItems = new ArrayList<>();
		order.getOrderedItems().forEach(item -> {
			BasketItem basketItem = new BasketItem();
			basketItem.setId(UUID.randomUUID().toString());
			basketItem.setName(item.getName());
			basketItem.setCategory1("Fries");
			basketItem.setItemType(BasketItemType.PHYSICAL.name());
			basketItem.setPrice(new BigDecimal(item.getPrice()));
			basketItems.add(basketItem);
		});
		iyzicoRequest.setBasketItems(basketItems);

		return CheckoutFormInitialize.create(iyzicoRequest, iyzicoOptions);
	}

	private OrderEntity convertToEntity(OrderRequest request) {
		return OrderEntity.builder().userId(request.getUserId()).userAddress(request.getUserAddress())
				.phoneNumber(request.getPhoneNumber()).email(request.getEmail()).amount(request.getAmount())
				.orderedItems(request.getOrderedItems()).build();
	}
}