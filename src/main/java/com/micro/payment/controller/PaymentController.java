package com.micro.payment.controller;

import com.micro.auth.dto.response.ApiResponse;
import com.micro.auth.schema.ErrorResponseSchema;
import com.micro.payment.dto.PaymentRequest;
import com.micro.payment.dto.PaymentResponse;
import com.micro.payment.schema.PaymentResponseSchema;
import com.micro.payment.service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(
        name = "Payment APIs",
        description = "Payment related APIs"
)
@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @Operation(
            summary = "Make payment",
            description = "Creates a new payment for the created order"
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "201",
                    description = "Payment completed successfully",
                    content = @Content(schema = @Schema(implementation = PaymentResponseSchema.class))
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "500",
                    description = "You are not allowed to pay this order",
                    content = @Content(schema = @Schema(implementation = ErrorResponseSchema.class))
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized",
                    content = @Content(schema = @Schema(implementation = ErrorResponseSchema.class))
            )
    })
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("/pay")
    public ResponseEntity<ApiResponse<PaymentResponse>> makePayment(
            @Valid @RequestBody PaymentRequest request
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ResponseEntity.ok(paymentService.makePayment(authentication, request));
    }
}
