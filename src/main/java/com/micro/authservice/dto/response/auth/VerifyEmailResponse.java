package com.micro.authservice.dto.response.auth;

public record VerifyEmailResponse(
        boolean verified,
        String message
) {
}
