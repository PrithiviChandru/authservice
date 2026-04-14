package com.micro.authservice.service.impl;

import com.micro.authservice.dto.response.ApiResponse;
import com.micro.authservice.dto.UserDetailsDto;
import com.micro.authservice.dto.request.*;
import com.micro.authservice.dto.response.LoginResponse;
import com.micro.authservice.dto.response.TokenValidationResponse;
import com.micro.authservice.entity.User;
import com.micro.authservice.enums.Role;
import com.micro.authservice.exception.ApiException;
import com.micro.authservice.repository.UserRepository;
import com.micro.authservice.security.JwtService;
import com.micro.authservice.security.TokenBlackListService;
import com.micro.authservice.service.AuthService;
import com.micro.authservice.util.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final TokenBlackListService tokenBlackListService;

    public ApiResponse<String> register(RegisterRequest request) {
        if (userRepository.findByEmail(request.email()).isPresent()) {
            throw ApiException.conflict("Email already exists");
        } else {
            Utils.validTimeZone(request.timeZone());
            User user = mapToUser(request);

            String verificationToken = UUID.randomUUID().toString();
            user.setVerified(false);
            user.setVerificationToken(verificationToken);
            user.setVerificationTokenExpiry(LocalDateTime.now().plusMinutes(30));

            User saved = userRepository.save(user);
            UserDetailsDto userDetails = mapToUserDetails(saved);
            return ApiResponse.success("User registered, Verify email", verificationToken);
        }
    }

    public ApiResponse<LoginResponse> login(LoginRequest request) {
        User user = userRepository.findByEmail(request.email()).orElseThrow(() -> ApiException.badRequest("Invalid email"));

        if (!user.isVerified())
            throw ApiException.unauthorized("Please verify your email first");

        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw ApiException.unauthorized("Invalid credentials");
        } else {
            String accessToken = jwtService.generateToken(user.getEmail());
            String refreshToken = UUID.randomUUID().toString();
            user.setRefreshToken(refreshToken);
            user.setRefreshTokenExpiry(LocalDateTime.now().plusDays(7));
            userRepository.save(user);

            UserDetailsDto userDetails = mapToUserDetails(user);
            LoginResponse response = new LoginResponse(accessToken, refreshToken, userDetails);
            return ApiResponse.success("Login successful", response);
        }
    }

    @Override
    public ApiResponse<Boolean> logout(String accessToken) {
        String email = jwtService.extractEmail(accessToken);
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> ApiException.unauthorized("User not found"));

        user.setRefreshToken(null);
        user.setRefreshTokenExpiry(null);
        userRepository.save(user);

        tokenBlackListService.blacklistToken(accessToken);
        return ApiResponse.success("Logout successful", true);
    }

    @Override
    public ApiResponse<LoginResponse> refreshToken(RefreshTokenRequest request) {
        User user = userRepository.findByRefreshToken(request.refreshToken())
                .orElseThrow(() -> ApiException.unauthorized("Invalid refresh token"));

        if (user.getRefreshTokenExpiry().isBefore(LocalDateTime.now()))
            throw ApiException.unauthorized("Refresh token expired");

        String newAccessToken = jwtService.generateToken(user.getEmail());
        String newRefreshToken = UUID.randomUUID().toString();

        user.setRefreshToken(newRefreshToken);
        user.setRefreshTokenExpiry(LocalDateTime.now().plusDays(7));
        userRepository.save(user);

        UserDetailsDto userDetails = mapToUserDetails(user);
        LoginResponse response = new LoginResponse(newAccessToken, newRefreshToken, userDetails);
        return ApiResponse.success("Token refreshed", response);
    }

    @Override
    public ApiResponse<Boolean> changePassword(String accessToken, ChangePasswordRequest request) {
        String email = jwtService.extractEmail(accessToken);
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> ApiException.unauthorized("User not found"));

        if (!passwordEncoder.matches(request.oldPassword(), user.getPassword()))
            throw ApiException.badRequest("Old password is incorrect");

        if (passwordEncoder.matches(request.newPassword(), user.getPassword()))
            throw ApiException.badRequest("New password cannot be same as old password");

        user.setPassword(passwordEncoder.encode(request.newPassword()));
        userRepository.save(user);
        return ApiResponse.success("Password changed successfully", true);
    }

    @Override
    public ApiResponse<String> forgetPassword(ForgotPasswordRequest request) {
        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> ApiException.badRequest("User nor found"));
        String resetToken = UUID.randomUUID().toString();

        user.setResetToken(resetToken);
        user.setResetTokenExpiry(LocalDateTime.now().plusMinutes(15));
        userRepository.save(user);

        return ApiResponse.success("Reset token generated", resetToken);
    }

    @Override
    public ApiResponse<Boolean> resetPassword(ResetPasswordRequest request) {
        User user = userRepository.findByResetToken(request.resetToken())
                .orElseThrow(() -> ApiException.badRequest("Invalid reset token"));

        if (user.getResetTokenExpiry().isBefore(LocalDateTime.now()))
            throw ApiException.badRequest("Token expired");

        user.setPassword(passwordEncoder.encode(request.newPassword()));
        user.setResetToken(null);
        user.setResetTokenExpiry(null);
        userRepository.save(user);

        return ApiResponse.success("Password reset successful", true);
    }

    @Override
    public ApiResponse<TokenValidationResponse> validateToken(String accessToken) {
        boolean isValid = jwtService.isTokenValid(accessToken);
        if (isValid) {
            String email = jwtService.extractEmail(accessToken);
            User user = userRepository.findByEmail(email)
                    .orElseThrow(() -> ApiException.unauthorized("User not found"));

            UserDetailsDto userDetails = mapToUserDetails(user);
            TokenValidationResponse response = new TokenValidationResponse(true, userDetails);
            return ApiResponse.success("Token is valid", response);
        } else {
            throw ApiException.unauthorized("Token expired or invalid");
        }
    }

    @Override
    public ApiResponse<Boolean> verifyEmail(String verificationToken) {
        User user = userRepository.findByVerificationToken(verificationToken)
                .orElseThrow(() -> ApiException.badRequest("Invalid token"));

        if (user.isVerified())
            throw ApiException.badRequest("Email already verified");

        if (user.getVerificationTokenExpiry().isBefore(LocalDateTime.now()))
            throw ApiException.badRequest("Token expired");

        user.setVerified(true);
        user.setVerificationToken(null);
        user.setVerificationTokenExpiry(null);
        userRepository.save(user);

        return ApiResponse.success("Email verification successful", true);
    }

    @Override
    public ApiResponse<String> resentVerification(ResentVerificationRequest request) {
        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> ApiException.badRequest("User not found"));

        if (user.isVerified())
            throw ApiException.badRequest("Email already verified");

        String verificationToken = UUID.randomUUID().toString();
        user.setVerificationToken(verificationToken);
        user.setVerificationTokenExpiry(LocalDateTime.now().plusMinutes(30));
        userRepository.save(user);

        return ApiResponse.success("Verification token resent", verificationToken);
    }


    private UserDetailsDto mapToUserDetails(User user) {
        return UserDetailsDto.builder()
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

    private User mapToUser(RegisterRequest request) {
        Instant instant = Instant.now();
        return User.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .phone(request.phone())
                .role(Role.USER)
                .timeZone(request.timeZone())
                .createdAt(instant)
                .updatedAt(instant)
                .build();
    }
}
