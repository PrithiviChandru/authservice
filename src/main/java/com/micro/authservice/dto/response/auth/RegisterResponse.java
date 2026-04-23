package com.micro.authservice.dto.response.auth;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class RegisterResponse implements Serializable {
    private String verifyToken;
    private LocalDateTime expiresAt;
    private String message;
}
