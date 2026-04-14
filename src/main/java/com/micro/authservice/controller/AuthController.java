package com.micro.authservice.controller;

import com.micro.authservice.dto.request.*;
import com.micro.authservice.dto.response.ApiResponse;
import com.micro.authservice.dto.response.LoginResponse;
import com.micro.authservice.dto.response.TokenValidationResponse;
import com.micro.authservice.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping(value = "/register")
    public ResponseEntity<ApiResponse<String>> register(
            @RequestBody @Valid RegisterRequest request
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(authService.register(request));
    }

    @PostMapping(value = "/login")
    public ResponseEntity<ApiResponse<LoginResponse>> login(
            @RequestBody LoginRequest request
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(authService.login(request));
    }

    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<Boolean>> logout(
            Authentication authentication
    ) {
        String accessToken = (String) authentication.getDetails();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(authService.logout(accessToken));
    }

    @GetMapping("/validate-token")
    public ResponseEntity<ApiResponse<TokenValidationResponse>> validateToken(
            Authentication authentication
    ) {
        String accessToken = (String) authentication.getDetails();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(authService.validateToken(accessToken));
    }

    @PostMapping("/change-password")
    public ResponseEntity<ApiResponse<Boolean>> changePassword(
            @RequestBody ChangePasswordRequest request,
            Authentication authentication
    ) {
        String accessToken = (String) authentication.getDetails();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(authService.changePassword(accessToken, request));
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<ApiResponse<String>> forgotPassword(
            @Valid @RequestBody ForgotPasswordRequest request
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(authService.forgetPassword(request));
    }

    @PostMapping("/reset-password")
    public ResponseEntity<ApiResponse<Boolean>> resetPassword(
            @Valid @RequestBody ResetPasswordRequest request
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(authService.resetPassword(request));
    }

    @PutMapping("/verify-email/{verificationToken}")
    public ResponseEntity<ApiResponse<Boolean>> verifyEmail(
            @PathVariable String verificationToken
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(authService.verifyEmail(verificationToken));
    }

    @PostMapping("/resent-verification")
    public ResponseEntity<ApiResponse<String>> resentVerification(
            @Valid @RequestBody ResentVerificationRequest request
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(authService.resentVerification(request));
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<ApiResponse<LoginResponse>> refreshToken(
            @Valid @RequestBody RefreshTokenRequest request
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(authService.refreshToken(request));
    }
}
