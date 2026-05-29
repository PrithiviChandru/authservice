package com.micro.product.schema;

import com.micro.auth.dto.response.ApiResponse;
import com.micro.auth.dto.response.PagedResponse;
import com.micro.product.dto.ProductResponse;

import java.time.LocalDateTime;
import java.util.List;

public class ProductListResponseSchema extends ApiResponse<PagedResponse<ProductResponse>> {
    public ProductListResponseSchema(boolean apiStatus, String message, PagedResponse<ProductResponse> data, Object errors, LocalDateTime timeStamp) {
        super(apiStatus, message, data, errors, timeStamp);
    }
}
