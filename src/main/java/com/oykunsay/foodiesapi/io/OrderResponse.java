package com.oykunsay.foodiesapi.io;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderResponse {
	private String id;
	private String userId;
	private String userAddress;
	private String phoneNumber;
	private String email;
	private double amount;
	private String paymentStatus;
	private String paymentId;
	private String paymentToken;
	private String basketId;
	private String orderStatus;
	private String paymentFormHtml;
}