package com.micro.authservice.service;

import com.micro.authservice.dto.request.UpdateProfileRequest;
import com.micro.authservice.dto.response.ApiResponse;
import com.micro.authservice.dto.UserDetailsDto;

import java.util.List;

public interface UserService {
    ApiResponse<List<UserDetailsDto>> getAllUsers();

    ApiResponse<UserDetailsDto> getUserById(Long id);

    ApiResponse<UserDetailsDto> updateProfile(String accessToken,UpdateProfileRequest request);

    ApiResponse<String> deleteById(Long id);
}
