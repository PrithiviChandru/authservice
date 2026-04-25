package com.micro.authservice.schema;

import com.micro.authservice.dto.response.ApiResponse;
import com.micro.authservice.dto.response.auth.ResetPasswordResponse;

import java.time.LocalDateTime;

public class ResetPasswordResponseSchema extends ApiResponse<ResetPasswordResponse> {
    public ResetPasswordResponseSchema(boolean apiStatus, String message, ResetPasswordResponse data, Object errors, LocalDateTime timeStamp) {
        super(apiStatus, message, data, errors, timeStamp);
    }
}
