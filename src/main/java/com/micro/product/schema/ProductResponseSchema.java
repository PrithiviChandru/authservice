package com.micro.product.schema;

import com.micro.auth.dto.response.ApiResponse;
import com.micro.product.dto.ProductResponse;

import java.time.LocalDateTime;

public class ProductResponseSchema extends ApiResponse<ProductResponse> {
    public ProductResponseSchema(boolean apiStatus, String message, ProductResponse data, Object errors, LocalDateTime timeStamp) {
        super(apiStatus, message, data, errors, timeStamp);
    }
}
