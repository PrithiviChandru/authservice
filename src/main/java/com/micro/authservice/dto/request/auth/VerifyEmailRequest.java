package com.micro.authservice.dto.request.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Verify email request payload")
public record VerifyEmailRequest(
        @NotBlank
        String verifyToken
) {
}
