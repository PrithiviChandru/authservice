package com.micro.authservice.dto.request.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Update profile request payload")
public record UpdateProfileRequest(
        @NotBlank
        @Schema(name = "firstName", example = "suresh")
        String firstName,

        @NotBlank
        @Schema(name = "lastName", example = "raina")
        String lastName,

        @NotBlank
        @Schema(name = "phone", example = "9876543210")
        String phone,

        @NotBlank
        @Schema(name = "tomeZone", example = "UTC")
        String timeZone
) {
}
