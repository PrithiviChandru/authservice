package com.micro.authservice.dto.response.auth;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ResendVerificationResponse {
    private String verificationToken;
    private LocalDateTime expiresAt;
    private String message;
}
