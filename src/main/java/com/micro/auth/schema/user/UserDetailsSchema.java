package com.micro.auth.schema.user;

import com.micro.auth.dto.UserResponse;
import com.micro.auth.dto.response.ApiResponse;

import java.time.LocalDateTime;

public class UserDetailsSchema extends ApiResponse<UserResponse> {
    public UserDetailsSchema(boolean apiStatus, String message, UserResponse data, Object errors, LocalDateTime timeStamp) {
        super(apiStatus, message, data, errors, timeStamp);
    }
}
