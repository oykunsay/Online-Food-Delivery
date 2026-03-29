package com.oykunsay.foodiesapi.service;

import com.oykunsay.foodiesapi.io.UserRequest;
import com.oykunsay.foodiesapi.io.UserResponse;

public interface UserService {

	UserResponse registerUser(UserRequest request);

}
