package com.micro.authservice.schema;

import com.micro.authservice.dto.response.ApiResponse;

import java.time.LocalDateTime;

public class ResentVerifyResponseSchema extends ApiResponse<ResentVerifyResponseSchema> {
    public ResentVerifyResponseSchema(boolean success, String message, ResentVerifyResponseSchema data, Object errors, LocalDateTime timeStamp) {
        super(success, message, data, errors, timeStamp);
    }
}
