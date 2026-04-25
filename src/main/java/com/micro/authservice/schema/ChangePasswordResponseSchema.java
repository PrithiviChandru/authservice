package com.micro.authservice.schema;

import com.micro.authservice.dto.response.ApiResponse;
import com.micro.authservice.dto.response.auth.ChangePasswordResponse;

import java.time.LocalDateTime;

public class ChangePasswordResponseSchema extends ApiResponse<ChangePasswordResponse> {
    public ChangePasswordResponseSchema(boolean success, String message, ChangePasswordResponse data, Object errors, LocalDateTime timeStamp) {
        super(success, message, data, errors, timeStamp);
    }
}
