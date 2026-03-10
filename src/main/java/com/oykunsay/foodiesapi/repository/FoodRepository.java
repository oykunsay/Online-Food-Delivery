package com.oykunsay.foodiesapi.repository;

import com.oykunsay.foodiesapi.entity.FoodEntity;
import com.oykunsay.foodiesapi.io.FoodResponse;
import com.oykunsay.foodiesapi.request.FoodRequest;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

@Repository
public interface FoodRepository extends MongoRepository<FoodEntity, String> {

	String uploadFile(MultipartFile file);

	FoodResponse addFood(FoodRequest request, MultipartFile file);
}
