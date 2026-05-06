package com.micro.auth.schema.user;

import com.micro.auth.dto.response.ApiResponse;
import com.micro.auth.dto.response.user.DeleteResponse;

import java.time.LocalDateTime;

public class DeleteResponseSchema extends ApiResponse<DeleteResponse> {
    public DeleteResponseSchema(boolean apiStatus, String message, DeleteResponse data, Object errors, LocalDateTime timeStamp) {
        super(apiStatus, message, data, errors, timeStamp);
    }
}
