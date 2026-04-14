package com.micro.authservice.dto.request;

import jakarta.validation.constraints.NotBlank;

public record ResentVerificationRequest(
        @NotBlank
        String email
) {
}
