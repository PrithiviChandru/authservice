package com.micro.authservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ApiResponse<T> {
    boolean success;
    String message;
    T data;
    Object errors;
    LocalDateTime timeStamp;

    public static <T> ApiResponse<T> success(String message, T data) {
        return new ApiResponse<>(true, message, data, null, LocalDateTime.now());
    }

    public static <T> ApiResponse<T> failure(String message, Object errors) {
        return new ApiResponse<>(false, message, null, errors, LocalDateTime.now());
    }
}
