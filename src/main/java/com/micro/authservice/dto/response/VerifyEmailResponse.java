package com.micro.authservice.dto.response;

public record VerifyEmailResponse(
        boolean verified,
        String message
) {
}
