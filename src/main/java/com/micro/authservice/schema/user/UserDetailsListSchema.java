package com.micro.authservice.schema.user;

import com.micro.authservice.dto.UserDetailsDto;
import com.micro.authservice.dto.response.ApiResponse;

import java.time.LocalDateTime;
import java.util.List;

public class UserDetailsListSchema extends ApiResponse<List<UserDetailsDto>> {
    public UserDetailsListSchema(boolean apiStatus, String message, List<UserDetailsDto> data, Object errors, LocalDateTime timeStamp) {
        super(apiStatus, message, data, errors, timeStamp);
    }
}
