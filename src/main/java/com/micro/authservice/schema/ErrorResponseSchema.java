package com.micro.authservice.schema;

import com.micro.authservice.dto.response.ApiResponse;

import java.time.LocalDateTime;

public class ErrorResponseSchema extends ApiResponse {
    public ErrorResponseSchema(boolean success, String message, Object data, Object errors, LocalDateTime timeStamp) {
        super(success, message, data, errors, timeStamp);
    }
}
