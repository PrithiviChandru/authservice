package com.micro.auth.schema.user;

import com.micro.auth.dto.UserResponse;
import com.micro.auth.dto.response.ApiResponse;

import java.time.LocalDateTime;
import java.util.List;

public class UserDetailsListSchema extends ApiResponse<List<UserResponse>> {
    public UserDetailsListSchema(boolean apiStatus, String message, List<UserResponse> data, Object errors, LocalDateTime timeStamp) {
        super(apiStatus, message, data, errors, timeStamp);
    }
}
