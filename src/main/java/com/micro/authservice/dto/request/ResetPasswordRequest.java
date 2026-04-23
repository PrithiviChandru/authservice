package com.micro.authservice.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Reset password request payload")
public record ResetPasswordRequest(
        @NotBlank
        String resetToken,

        @NotBlank
        String newPassword
) {
}
