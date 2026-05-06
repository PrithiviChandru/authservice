package com.micro.auth.schema;

import com.micro.auth.dto.response.ApiResponse;
import com.micro.auth.dto.response.auth.ForgotPasswordResponse;

import java.time.LocalDateTime;

public class ForgotPasswordResponseSchema extends ApiResponse<ForgotPasswordResponse> {
    public ForgotPasswordResponseSchema(boolean apiStatus, String message, ForgotPasswordResponse data, Object errors, LocalDateTime timeStamp) {
        super(apiStatus, message, data, errors, timeStamp);
    }
}
