package com.micro.order.schema;

import com.micro.auth.dto.response.ApiResponse;
import com.micro.order.dto.OrderResponse;

import java.time.LocalDateTime;

public class OrderResponseSchema extends ApiResponse<OrderResponse> {
    public OrderResponseSchema(boolean apiStatus, String message, OrderResponse data, Object errors, LocalDateTime timeStamp) {
        super(apiStatus, message, data, errors, timeStamp);
    }
}
