package com.micro.auth.schema;


import com.micro.auth.dto.response.ApiResponse;
import com.micro.auth.dto.response.auth.RegisterResponse;

import java.time.LocalDateTime;

public class RegisterResponseSchema extends ApiResponse<RegisterResponse> {
    public RegisterResponseSchema(boolean success, String message, RegisterResponse data, Object errors, LocalDateTime timeStamp) {
        super(success, message, data, errors, timeStamp);
    }
}
