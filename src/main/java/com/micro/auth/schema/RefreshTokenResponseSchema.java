package com.micro.auth.schema;

import com.micro.auth.dto.response.ApiResponse;
import com.micro.auth.dto.response.auth.RefreshTokenResponse;

import java.time.LocalDateTime;

public class RefreshTokenResponseSchema extends ApiResponse<RefreshTokenResponse> {
    public RefreshTokenResponseSchema(boolean success, String message, RefreshTokenResponse data, Object errors, LocalDateTime timeStamp) {
        super(success, message, data, errors, timeStamp);
    }
}
