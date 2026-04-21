package com.micro.authservice.schema;

import com.micro.authservice.dto.response.ApiResponse;
import com.micro.authservice.dto.response.TokenValidationResponse;

import java.time.LocalDateTime;

public class TokenValidationResponseSchema extends ApiResponse<TokenValidationResponse> {
    public TokenValidationResponseSchema(boolean success, String message, TokenValidationResponse data, Object errors, LocalDateTime timeStamp) {
        super(success, message, data, errors, timeStamp);
    }
}
