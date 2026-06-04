package com.micro.category.schema;

import com.micro.auth.dto.response.ApiResponse;
import com.micro.category.dto.CategoryResponse;

import java.time.LocalDateTime;

public class CategoryResponseSchema extends ApiResponse<CategoryResponse> {
    public CategoryResponseSchema(boolean apiStatus, String message, CategoryResponse data, Object errors, LocalDateTime timeStamp) {
        super(apiStatus, message, data, errors, timeStamp);
    }
}
