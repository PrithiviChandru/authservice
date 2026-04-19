package com.micro.authservice.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Schema(name = "RegisterRequest")
public record RegisterRequest(
        @NotBlank(message = "First Name is required")
        @Schema(name = "firstName",example = "John")
        String firstName,

        @Schema(name = "lastName",example = "Michel")
        String lastName,

        @NotBlank(message = "Email is required")
        @Email
        @Schema(name = "email",example = "john.michel@example.com")
        String email,

        @NotBlank(message = "Password is required")
        @Schema(name = "password",example = "StrongPass@123")
        String password,

        @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be 10 digits")
        @Schema(name = "phone",example = "9876543210")
        String phone,

        @NotBlank(message = "TomeZone is required")
        @Schema(name = "timeZone",example = "Asia/Kolkata")
        String timeZone
) {
}
