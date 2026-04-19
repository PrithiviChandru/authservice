package com.micro.authservice.schema;

import com.micro.authservice.dto.response.ApiResponse;
import com.micro.authservice.dto.response.VerifyEmailResponse;

import java.time.LocalDateTime;

public class VerifyEmailResponseSchema extends ApiResponse<VerifyEmailResponse> {
    public VerifyEmailResponseSchema(boolean success, String message, VerifyEmailResponse data, Object errors, LocalDateTime timeStamp) {
        super(success, message, data, errors, timeStamp);
    }
}
