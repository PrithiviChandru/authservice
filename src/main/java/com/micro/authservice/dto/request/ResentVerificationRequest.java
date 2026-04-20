package com.micro.authservice.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Resent verification request payload")
public record ResentVerificationRequest(
        @NotBlank(message = "Email is required")
        @Email
        @Schema(name = "email", example = "user@example.com")
        String email
) {
}
