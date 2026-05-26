package com.micro.auth.dto.response.auth;

public record VerifyEmailResponse(
        boolean verified,
        String message
) {
}
