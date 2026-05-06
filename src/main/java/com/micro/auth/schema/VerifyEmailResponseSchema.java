package com.micro.auth.schema;

import com.micro.auth.dto.response.ApiResponse;
import com.micro.auth.dto.response.auth.VerifyEmailResponse;

import java.time.LocalDateTime;

public class VerifyEmailResponseSchema extends ApiResponse<VerifyEmailResponse> {
    public VerifyEmailResponseSchema(boolean success, String message, VerifyEmailResponse data, Object errors, LocalDateTime timeStamp) {
        super(success, message, data, errors, timeStamp);
    }
}
