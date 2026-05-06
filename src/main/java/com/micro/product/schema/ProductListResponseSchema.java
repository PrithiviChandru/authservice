package com.micro.product.schema;

import com.micro.auth.dto.response.ApiResponse;
import com.micro.product.dto.ProductResponse;

import java.time.LocalDateTime;
import java.util.List;

public class ProductListResponseSchema extends ApiResponse<List<ProductResponse>> {
    public ProductListResponseSchema(boolean apiStatus, String message, List<ProductResponse> data, Object errors, LocalDateTime timeStamp) {
        super(apiStatus, message, data, errors, timeStamp);
    }
}
