package com.micro.authservice.schema.user;

import com.micro.authservice.dto.UserResponse;
import com.micro.authservice.dto.response.ApiResponse;

import java.time.LocalDateTime;

public class UserDetailsSchema extends ApiResponse<UserResponse> {
    public UserDetailsSchema(boolean apiStatus, String message, UserResponse data, Object errors, LocalDateTime timeStamp) {
        super(apiStatus, message, data, errors, timeStamp);
    }
}
