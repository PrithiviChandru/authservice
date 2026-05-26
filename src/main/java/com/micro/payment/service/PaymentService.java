package com.micro.payment.service;

import com.micro.auth.dto.response.ApiResponse;
import com.micro.payment.dto.PaymentRequest;
import com.micro.payment.dto.PaymentResponse;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface PaymentService {
    ApiResponse<PaymentResponse> makePayment(Authentication authentication, PaymentRequest request);

    ApiResponse<List<PaymentResponse>> myPayments(Authentication authentication);

    ApiResponse<PaymentResponse> getPayment(Authentication authentication, Long id);
}
