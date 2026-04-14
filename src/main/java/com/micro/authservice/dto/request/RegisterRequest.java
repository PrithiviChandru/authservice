package com.micro.authservice.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record RegisterRequest(
        @NotBlank(message = "First Name is required")
        String firstName,

        String lastName,

        @Email
        String email,

        @NotBlank(message = "Password is required")
        String password,

        @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be 10 digits")
        String phone,

        @NotBlank(message = "TomeZone is required")
        String timeZone
) {
}
