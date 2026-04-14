package com.micro.authservice.service;

import com.micro.authservice.dto.request.*;
import com.micro.authservice.dto.response.ApiResponse;
import com.micro.authservice.dto.response.LoginResponse;
import com.micro.authservice.dto.response.TokenValidationResponse;

public interface AuthService {
    ApiResponse<String> register(RegisterRequest request);

    ApiResponse<LoginResponse> login(LoginRequest request);

    ApiResponse<Boolean> logout(String accessToken);

    ApiResponse<LoginResponse> refreshToken(RefreshTokenRequest request);

    ApiResponse<Boolean> changePassword(String accessToken, ChangePasswordRequest request);

    ApiResponse<String> forgetPassword(ForgotPasswordRequest request);

    ApiResponse<Boolean> resetPassword(ResetPasswordRequest request);

    ApiResponse<TokenValidationResponse> validateToken(String accessToken);

    ApiResponse<Boolean> verifyEmail(String verificationToken);

    ApiResponse<String> resentVerification(ResentVerificationRequest request);
}
