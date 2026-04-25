package com.micro.authservice.service.impl;

import com.micro.authservice.dto.UserResponse;
import com.micro.authservice.dto.request.auth.UpdateProfileRequest;
import com.micro.authservice.dto.response.ApiResponse;
import com.micro.authservice.dto.response.user.DeleteResponse;
import com.micro.authservice.entity.User;
import com.micro.authservice.exception.ApiException;
import com.micro.authservice.repository.UserRepository;
import com.micro.authservice.security.JwtService;
import com.micro.authservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final JwtService jwtService;

    @Override
    public ApiResponse<UserResponse> updateProfile(String accessToken, UpdateProfileRequest request) {
        String email = jwtService.extractEmail(accessToken);
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> ApiException.unauthorized("User not found"));

        if (null != request.firstName())
            user.setFirstName(request.firstName());
        if (null != request.lastName())
            user.setLastName(request.lastName());
        if (null != request.phone())
            user.setPhone(request.phone());
        if (null != request.timeZone())
            user.setTimeZone(request.timeZone());
        userRepository.save(user);

        UserResponse userInfo = mapToUserDetails(user);
        return ApiResponse.success("Profile updated successfully", userInfo);
    }

    @Override
    public ApiResponse<UserResponse> getProfile(String accessToken) {
        String email = jwtService.extractEmail(accessToken);
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> ApiException.notFound("User not found"));

        UserResponse userInfo = mapToUserDetails(user);
        return ApiResponse.success("Profile retrieved successful", userInfo);
    }

    @Override
    public ApiResponse<List<UserResponse>> getAllUsers() {
        List<User> userList = userRepository.findAll();
        List<UserResponse> userInfoList = userList.stream().map(user -> mapToUserDetails(user)).collect(Collectors.toUnmodifiableList());
        return ApiResponse.success("User list fetched", userInfoList);
    }

    @Override
    public ApiResponse<UserResponse> getUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> ApiException.notFound("Entity not found"));
        UserResponse userInfo = mapToUserDetails(user);
        return ApiResponse.success("User date fetched", userInfo);
    }

    @Override
    public ApiResponse<DeleteResponse> deleteUser(Long id) {
        userRepository.deleteById(id);
        DeleteResponse response = new DeleteResponse(
                true,
                id,
                "User deleted successfully"
        );
        return ApiResponse.success("User deletion successful", response);
    }

    private UserResponse mapToUserDetails(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .role(user.getRole())
                .timeZone(user.getTimeZone())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }
}
