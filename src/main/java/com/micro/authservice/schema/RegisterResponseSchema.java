package com.micro.authservice.schema;


import com.micro.authservice.dto.response.ApiResponse;
import com.micro.authservice.dto.response.RegisterResponse;

import java.time.LocalDateTime;

public class RegisterResponseSchema extends ApiResponse<RegisterResponse> {
    public RegisterResponseSchema(boolean success, String message, RegisterResponse data, Object errors, LocalDateTime timeStamp) {
        super(success, message, data, errors, timeStamp);
    }
}
