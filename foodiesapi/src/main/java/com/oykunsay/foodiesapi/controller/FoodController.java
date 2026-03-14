package com.oykunsay.foodiesapi.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.oykunsay.foodiesapi.io.FoodResponse;
import com.oykunsay.foodiesapi.request.FoodRequest;
import com.oykunsay.foodiesapi.service.FoodService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/foods")
@AllArgsConstructor
public class FoodController {

	private final FoodService foodService;

	@PostMapping
	public FoodResponse addFood(@RequestPart("food") FoodRequest request, @RequestPart("file") MultipartFile file) {

		return foodService.addFood(request, file);
	}

	@GetMapping
	public List<FoodResponse> readFoods() {
		return foodService.readFoods();
	}

	@GetMapping("/{id}")
	public FoodResponse readFood(@PathVariable String id) {
		return foodService.readFood(id);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteFood(@PathVariable String id) {
		foodService.deleteFood(id);

	}
}