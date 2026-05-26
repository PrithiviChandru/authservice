package com.micro.order.schema;

import com.micro.auth.dto.response.ApiResponse;
import com.micro.order.dto.OrderResponse;

import java.time.LocalDateTime;
import java.util.List;

public class OrderListResponseSchema extends ApiResponse<List<OrderResponse>> {
    public OrderListResponseSchema(boolean apiStatus, String message, List<OrderResponse> data, Object errors, LocalDateTime timeStamp) {
        super(apiStatus, message, data, errors, timeStamp);
    }
}
