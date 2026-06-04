package com.micro.category.schema;

import com.micro.auth.dto.response.ApiResponse;
import com.micro.auth.dto.response.PagedResponse;
import com.micro.category.dto.CategoryResponse;

import java.time.LocalDateTime;

public class CategoryListResponseSchema extends ApiResponse<PagedResponse<CategoryResponse>> {
    public CategoryListResponseSchema(boolean apiStatus, String message, PagedResponse<CategoryResponse> data, Object errors, LocalDateTime timeStamp) {
        super(apiStatus, message, data, errors, timeStamp);
    }
}
