package com.micro.authservice.dto.request.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Refresh token request payload")
public record RefreshTokenRequest(
        @NotBlank
        String refreshToken
) {
}
