package com.micro.payment.schema;

import com.micro.auth.dto.response.ApiResponse;
import com.micro.payment.dto.PaymentResponse;

import java.time.LocalDateTime;

public class PaymentResponseSchema extends ApiResponse<PaymentResponse> {
    public PaymentResponseSchema(boolean apiStatus, String message, PaymentResponse data, Object errors, LocalDateTime timeStamp) {
        super(apiStatus, message, data, errors, timeStamp);
    }
}
