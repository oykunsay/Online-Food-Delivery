package com.oykunsay.foodiesapi.io;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderRequest {
	private String userId;
	private List<OrderItem> orderedItems;
	private String userAddress;
	private double amount;
	private String phoneNumber;
	private String email;
	private String firstName;
	private String lastName;
}