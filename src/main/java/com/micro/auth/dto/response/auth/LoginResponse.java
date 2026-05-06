package com.micro.auth.dto.response.auth;

import com.micro.auth.dto.UserResponse;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class LoginResponse implements Serializable {
    private String accessToken;
    private String refreshToken;
    private UserResponse userInfo;
}
