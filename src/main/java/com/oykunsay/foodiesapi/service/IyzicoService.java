package com.oykunsay.foodiesapi.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IyzicoService {

	private final Options iyzicoOptions;

	public CheckoutFormInitialize prepareCheckoutForm(OrderEntity order, OrderRequest request) {
		CreateCheckoutFormInitializeRequest iyzicoRequest = new CreateCheckoutFormInitializeRequest();
		iyzicoRequest.setLocale(Locale.TR.getValue());
		iyzicoRequest.setConversationId(order.getId());
		iyzicoRequest.setPrice(new BigDecimal(order.getAmount()));
		iyzicoRequest.setPaidPrice(new BigDecimal(order.getAmount()));
		iyzicoRequest.setCurrency(Currency.TRY.name());
		iyzicoRequest.setBasketId(order.getId());
		iyzicoRequest.setPaymentGroup(PaymentGroup.PRODUCT.name());
		iyzicoRequest.setCallbackUrl("http://localhost:8080/api/payments/callback");

		iyzicoRequest.setBuyer(prepareBuyer(order, request));
		iyzicoRequest.setShippingAddress(prepareAddress(order, request));
		iyzicoRequest.setBillingAddress(prepareAddress(order, request));
		iyzicoRequest.setBasketItems(prepareBasketItems(order));

		return CheckoutFormInitialize.create(iyzicoRequest, iyzicoOptions);
	}

	private Buyer prepareBuyer(OrderEntity order, OrderRequest request) {
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
		return buyer;
	}

	private Address prepareAddress(OrderEntity order, OrderRequest request) {
		Address address = new Address();
		address.setContactName(request.getFirstName() + " " + request.getLastName());
		address.setCity("Trabzon");
		address.setCountry("Turkey");
		address.setAddress(order.getUserAddress());
		return address;
	}

	private List<BasketItem> prepareBasketItems(OrderEntity order) {
		List<BasketItem> basketItems = new ArrayList<>();
		order.getOrderedItems().forEach(item -> {
			BasketItem basketItem = new BasketItem();
			basketItem.setId(UUID.randomUUID().toString());
			basketItem.setName(item.getName());
			basketItem.setCategory1(item.getCategory() != null ? item.getCategory() : "Food");
			basketItem.setItemType(BasketItemType.PHYSICAL.name());
			double totalItemPrice = item.getPrice() * (item.getQuantity() > 0 ? item.getQuantity() : 1);
			basketItem.setPrice(new BigDecimal(totalItemPrice));

			basketItems.add(basketItem);
		});
		return basketItems;
	}
}
