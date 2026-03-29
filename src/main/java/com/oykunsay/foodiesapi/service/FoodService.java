package com.oykunsay.foodiesapi.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.oykunsay.foodiesapi.io.FoodRequest;
import com.oykunsay.foodiesapi.io.FoodResponse;

public interface FoodService {

	String uploadFile(MultipartFile file);

	public boolean deleteFile(String fileName);

	FoodResponse addFood(FoodRequest request, MultipartFile file);

	List<FoodResponse> readFoods();

	void deleteFood(String id);

	FoodResponse getFoodById(String id);
}
