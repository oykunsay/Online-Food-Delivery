package com.oykunsay.foodiesapi.service;

import org.springframework.web.multipart.MultipartFile;

import com.oykunsay.foodiesapi.io.FoodResponse;
import com.oykunsay.foodiesapi.request.FoodRequest;

public interface FoodService {

	String uploadFile(MultipartFile file);

	FoodResponse addFood(FoodRequest request, MultipartFile file);
}
