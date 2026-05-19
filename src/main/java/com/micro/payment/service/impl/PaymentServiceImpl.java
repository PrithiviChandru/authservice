package com.micro.payment.service.impl;

import com.micro.auth.dto.response.ApiResponse;
import com.micro.payment.dto.PaymentRequest;
import com.micro.payment.dto.PaymentResponse;
import com.micro.payment.repository.PaymentRepository;
import com.micro.payment.service.PaymentService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;

    @Override
    @Transactional
    public ApiResponse<PaymentResponse> makePayment(Authentication authentication, PaymentRequest request) {
        return null;
    }

    @Override
    public ApiResponse<List<PaymentResponse>> myPayments() {
        return null;
    }

    @Override
    public ApiResponse<PaymentResponse> getPayment(Long id) {
        return null;
    }
}
