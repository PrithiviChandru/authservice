package com.micro.authservice.service;

import com.micro.authservice.dto.request.auth.UpdateProfileRequest;
import com.micro.authservice.dto.response.ApiResponse;
import com.micro.authservice.dto.UserDetailsDto;
import com.micro.authservice.dto.response.user.DeleteResponse;

import java.util.List;

public interface UserService {
    ApiResponse<UserDetailsDto> updateProfile(String accessToken,UpdateProfileRequest request);

    ApiResponse<List<UserDetailsDto>> getAllUsers();

    ApiResponse<UserDetailsDto> getUserById(Long id);

    ApiResponse<DeleteResponse> deleteById(Long id);
}
