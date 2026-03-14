package com.oykunsay.foodiesapi.service;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.oykunsay.foodiesapi.entity.FoodEntity;
import com.oykunsay.foodiesapi.io.FoodResponse;
import com.oykunsay.foodiesapi.repository.FoodRepository;
import com.oykunsay.foodiesapi.request.FoodRequest;

import lombok.RequiredArgsConstructor;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.ObjectCannedACL;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

@Service
@RequiredArgsConstructor
public class FoodServiceImpl implements FoodService {

	private final S3Client s3Client;
	private final FoodRepository foodRepository;

	@Value("${aws.s3.bucketname}")
	private String bucketName;

	@Override
	public String uploadFile(MultipartFile file) {
		String filenameExtension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
		String key = UUID.randomUUID().toString() + filenameExtension;
		try {
			PutObjectRequest putObjectRequest = PutObjectRequest.builder().bucket(bucketName).key(key)
					.acl(ObjectCannedACL.PUBLIC_READ).contentType(file.getContentType()).build();
			PutObjectResponse response = s3Client.putObject(putObjectRequest, RequestBody.fromBytes(file.getBytes()));

			if (response.sdkHttpResponse().isSuccessful()) {
				return "https://" + bucketName + ".s3.amazonaws.com/" + key;
			} else {
				throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "File upload failed.");
			}
		} catch (IOException ex) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
					"An error occured while uploading file.");

		}
	}

	@Override
	public FoodResponse addFood(FoodRequest request, MultipartFile file) {
		FoodEntity newFoodEntity = convertToEntity(request);
		String imageUrl = uploadFile(file);
		newFoodEntity.setImageUrl(imageUrl);
		foodRepository.save(newFoodEntity);
		return convertToResponse(newFoodEntity);

	}

	private FoodEntity convertToEntity(FoodRequest request) {
		return FoodEntity.builder().name(request.getName()).description(request.getDescription())
				.category(request.getCategory()).price(request.getPrice()).build();

	}

	private FoodResponse convertToResponse(FoodEntity entity) {
		return FoodResponse.builder().id(entity.getId()).name(entity.getName()).description(entity.getDescription())
				.category(entity.getCategory()).price(entity.getPrice()).imageUrl(entity.getImageUrl()).build();
	}

	@Override
	public List<FoodResponse> readFoods() {
		return foodRepository.findAll().stream().map(this::convertToResponse).collect(Collectors.toList());
	}

}
