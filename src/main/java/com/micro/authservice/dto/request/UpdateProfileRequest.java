package com.micro.authservice.dto.request;

public record UpdateProfileRequest(
        String firstName,
        String lastName,
        String phone,
        String timeZone
) {
}
