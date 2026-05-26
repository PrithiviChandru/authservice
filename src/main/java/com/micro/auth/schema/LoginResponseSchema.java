package com.micro.auth.schema;

import com.micro.auth.dto.response.ApiResponse;
import com.micro.auth.dto.response.auth.LoginResponse;

import java.time.LocalDateTime;

public class LoginResponseSchema extends ApiResponse<LoginResponse> {
    public LoginResponseSchema(boolean success, String message, LoginResponse data, Object errors, LocalDateTime timeStamp) {
        super(success, message, data, errors, timeStamp);
    }
}
