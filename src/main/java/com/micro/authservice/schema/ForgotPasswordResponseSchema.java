package com.micro.authservice.schema;

import com.micro.authservice.dto.response.ApiResponse;
import com.micro.authservice.dto.response.ForgotPasswordResponse;

import java.time.LocalDateTime;

public class ForgotPasswordResponseSchema extends ApiResponse<ForgotPasswordResponse> {
    public ForgotPasswordResponseSchema(boolean apiStatus, String message, ForgotPasswordResponse data, Object errors, LocalDateTime timeStamp) {
        super(apiStatus, message, data, errors, timeStamp);
    }
}
