package com.micro.authservice.service;

import com.micro.authservice.dto.UserResponse;
import com.micro.authservice.dto.request.auth.UpdateProfileRequest;
import com.micro.authservice.dto.response.ApiResponse;
import com.micro.authservice.dto.response.user.DeleteResponse;

import java.util.List;

public interface UserService {
    ApiResponse<UserResponse> updateProfile(String accessToken, UpdateProfileRequest request);

    ApiResponse<UserResponse> getProfile(String accessToken);

    ApiResponse<List<UserResponse>> getAllUsers();

    ApiResponse<UserResponse> getUser(Long id);

    ApiResponse<DeleteResponse> deleteUser(Long id);
}
