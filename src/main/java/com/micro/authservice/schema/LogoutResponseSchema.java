package com.micro.authservice.schema;

import com.micro.authservice.dto.response.ApiResponse;
import com.micro.authservice.dto.response.auth.LogoutResponse;

import java.time.LocalDateTime;

public class LogoutResponseSchema extends ApiResponse<LogoutResponse> {
    public LogoutResponseSchema(boolean success, String message, LogoutResponse data, Object errors, LocalDateTime timeStamp) {
        super(success, message, data, errors, timeStamp);
    }
}
