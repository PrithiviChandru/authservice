package com.micro.auth.schema;

import com.micro.auth.dto.response.ApiResponse;

import java.time.LocalDateTime;

public class ResentVerifyResponseSchema extends ApiResponse<ResentVerifyResponseSchema> {
    public ResentVerifyResponseSchema(boolean success, String message, ResentVerifyResponseSchema data, Object errors, LocalDateTime timeStamp) {
        super(success, message, data, errors, timeStamp);
    }
}
