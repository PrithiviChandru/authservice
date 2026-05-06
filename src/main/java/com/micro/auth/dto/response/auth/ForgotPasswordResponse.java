package com.micro.auth.dto.response.auth;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ForgotPasswordResponse {
    private String resetToken;
    private LocalDateTime expiresAt;
    private String message;
}
