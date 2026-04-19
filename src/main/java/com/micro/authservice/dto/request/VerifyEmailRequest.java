package com.micro.authservice.dto.request;

import jakarta.validation.constraints.NotBlank;

public record VerifyEmailRequest(
        @NotBlank
        String verifyToken
) {
}
