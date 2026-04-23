package com.micro.authservice.dto.request.auth;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Token validation request payload")
public record TokenValidationRequest(
        String accessToken
) {
}
