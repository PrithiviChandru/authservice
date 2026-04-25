package com.micro.authservice.dto.response.auth;

import com.micro.authservice.dto.UserResponse;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RefreshTokenResponse {
    private String accessToken;
    private String refreshToken;
    private UserResponse userInfo;
}
