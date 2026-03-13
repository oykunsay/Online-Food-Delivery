package com.oykunsay.foodiesapi.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.oykunsay.foodiesapi.io.FoodResponse;
import com.oykunsay.foodiesapi.request.FoodRequest;

public interface FoodService {

	String uploadFile(MultipartFile file);

	FoodResponse addFood(FoodRequest request, MultipartFile file);

	List<FoodResponse> readFoods();
}
