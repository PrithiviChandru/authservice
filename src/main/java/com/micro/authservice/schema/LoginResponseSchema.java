package com.micro.authservice.schema;

import com.micro.authservice.dto.response.ApiResponse;
import com.micro.authservice.dto.response.LoginResponse;

import java.time.LocalDateTime;

public class LoginResponseSchema extends ApiResponse<LoginResponse> {
    public LoginResponseSchema(boolean success, String message, LoginResponse data, Object errors, LocalDateTime timeStamp) {
        super(success, message, data, errors, timeStamp);
    }
}
