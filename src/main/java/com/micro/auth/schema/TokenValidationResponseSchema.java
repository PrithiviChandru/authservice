package com.micro.auth.schema;

import com.micro.auth.dto.response.ApiResponse;
import com.micro.auth.dto.response.auth.TokenValidationResponse;

import java.time.LocalDateTime;

public class TokenValidationResponseSchema extends ApiResponse<TokenValidationResponse> {
    public TokenValidationResponseSchema(boolean success, String message, TokenValidationResponse data, Object errors, LocalDateTime timeStamp) {
        super(success, message, data, errors, timeStamp);
    }
}
