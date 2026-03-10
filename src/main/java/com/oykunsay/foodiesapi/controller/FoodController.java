package com.oykunsay.foodiesapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.oykunsay.foodiesapi.io.FoodResponse;
import com.oykunsay.foodiesapi.request.FoodRequest;
import com.oykunsay.foodiesapi.service.FoodService;

import lombok.AllArgsConstructor;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api/foods")
@AllArgsConstructor
public class FoodController {

	private final FoodService foodService;

	@PostMapping
	public FoodResponse addFood(@RequestPart("food") String foodString, @RequestPart("file") MultipartFile file) {
		ObjectMapper objectMapper = new ObjectMapper();
		FoodRequest request = null;
		try {
			request = objectMapper.readValue(foodString, FoodRequest.class);
		} catch (Exception ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid JSON format");
		}
		FoodResponse response = foodService.addFood(request, file);
		return response;

	}

}
