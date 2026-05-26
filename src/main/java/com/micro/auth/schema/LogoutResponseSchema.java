package com.micro.auth.schema;

import com.micro.auth.dto.response.ApiResponse;
import com.micro.auth.dto.response.auth.LogoutResponse;

import java.time.LocalDateTime;

public class LogoutResponseSchema extends ApiResponse<LogoutResponse> {
    public LogoutResponseSchema(boolean success, String message, LogoutResponse data, Object errors, LocalDateTime timeStamp) {
        super(success, message, data, errors, timeStamp);
    }
}
