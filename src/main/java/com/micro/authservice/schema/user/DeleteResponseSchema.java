package com.micro.authservice.schema.user;

import com.micro.authservice.dto.response.ApiResponse;
import com.micro.authservice.dto.response.user.DeleteResponse;

import java.time.LocalDateTime;

public class DeleteResponseSchema extends ApiResponse<DeleteResponse> {
    public DeleteResponseSchema(boolean apiStatus, String message, DeleteResponse data, Object errors, LocalDateTime timeStamp) {
        super(apiStatus, message, data, errors, timeStamp);
    }
}
