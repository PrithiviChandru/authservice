package com.micro.auth.service;

import com.micro.auth.dto.request.auth.*;
import com.micro.auth.dto.response.*;
import com.micro.auth.dto.response.auth.*;

public interface AuthService {
    ApiResponse<RegisterResponse> register(RegisterRequest request);

    ApiResponse<VerifyEmailResponse> verifyEmail(VerifyEmailRequest request);

    ApiResponse<ResendVerificationResponse> resentVerification(ResentVerificationRequest request);

    ApiResponse<LoginResponse> login(LoginRequest request);

    ApiResponse<LogoutResponse> logout(String accessToken);

    ApiResponse<TokenValidationResponse> validateToken(String accessToken);

    ApiResponse<RefreshTokenResponse> refreshToken(RefreshTokenRequest request);

    ApiResponse<ChangePasswordResponse> changePassword(String accessToken, ChangePasswordRequest request);

    ApiResponse<ForgotPasswordResponse> forgetPassword(ForgotPasswordRequest request);

    ApiResponse<ResetPasswordResponse> resetPassword(ResetPasswordRequest request);
}
