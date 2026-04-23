package com.micro.authservice.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;

@Schema(description = "Forgot password request payload")
public record ForgotPasswordRequest(
        @Email
        @Schema(name = "email", example = "user@example.com")
        String email
) {
}
