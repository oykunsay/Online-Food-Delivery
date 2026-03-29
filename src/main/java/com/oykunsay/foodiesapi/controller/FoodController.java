package com.oykunsay.foodiesapi.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*; 
import org.springframework.web.multipart.MultipartFile;

import com.oykunsay.foodiesapi.io.FoodRequest;
import com.oykunsay.foodiesapi.io.FoodResponse;
import com.oykunsay.foodiesapi.service.FoodService;
import lombok.AllArgsConstructor;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/foods")
@AllArgsConstructor
public class FoodController {

	private final FoodService foodService;

	@PostMapping
	public FoodResponse addFood(@RequestPart("food") FoodRequest request, @RequestPart("file") MultipartFile file) {
		return foodService.addFood(request, file);
	}
	@GetMapping("/{id}")
	public FoodResponse getFoodById(@PathVariable String id) {
	    return foodService.getFoodById(id); 
	}
	@GetMapping
	public List<FoodResponse> readFoods() {
		return foodService.readFoods();
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteFood(@PathVariable String id) {
		foodService.deleteFood(id);
	}
    
}