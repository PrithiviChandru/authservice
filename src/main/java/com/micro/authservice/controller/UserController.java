package com.micro.authservice.controller;

import com.micro.authservice.dto.request.UpdateProfileRequest;
import com.micro.authservice.dto.response.ApiResponse;
import com.micro.authservice.dto.UserDetailsDto;
import com.micro.authservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/list")
    public ResponseEntity<ApiResponse<List<UserDetailsDto>>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ApiResponse<UserDetailsDto>> getUserById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PutMapping("/me")
    public ResponseEntity<ApiResponse<UserDetailsDto>> updateProfile(
            @RequestBody UpdateProfileRequest request,
            Authentication authentication
    ) {
        String accessToken = (String) authentication.getDetails();
        return ResponseEntity.ok(userService.updateProfile(accessToken, request));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<String>> deleteById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userService.deleteById(id));
    }
}
