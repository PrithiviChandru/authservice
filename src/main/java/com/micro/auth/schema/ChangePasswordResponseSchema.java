package com.micro.auth.schema;

import com.micro.auth.dto.response.ApiResponse;
import com.micro.auth.dto.response.auth.ChangePasswordResponse;

import java.time.LocalDateTime;

public class ChangePasswordResponseSchema extends ApiResponse<ChangePasswordResponse> {
    public ChangePasswordResponseSchema(boolean success, String message, ChangePasswordResponse data, Object errors, LocalDateTime timeStamp) {
        super(success, message, data, errors, timeStamp);
    }
}
