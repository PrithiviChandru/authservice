package com.micro.authservice.schema.user;

import com.micro.authservice.dto.UserDetailsDto;
import com.micro.authservice.dto.response.ApiResponse;

import java.time.LocalDateTime;

public class UserDetailsSchema extends ApiResponse<UserDetailsDto> {
    public UserDetailsSchema(boolean apiStatus, String message, UserDetailsDto data, Object errors, LocalDateTime timeStamp) {
        super(apiStatus, message, data, errors, timeStamp);
    }
}
