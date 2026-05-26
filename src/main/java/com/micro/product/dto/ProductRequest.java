package com.micro.product.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

@Schema(description = "Product request payload")
public record ProductRequest(
        @NotBlank(message = "Product name is required")
        @Schema(name = "name", example = "Wireless Mouse")
        String name,

        @NotBlank(message = "Description is required")
        @Schema(name = "description", example = "Bluetooth rechargeable mouse")
        String description,

        @NotNull(message = "Price is required")
        @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
        @Schema(name = "price", example = "799.00")
        BigDecimal price,

        @NotNull(message = "Stock is required")
        @Min(value = 0, message = "Stock can not be negative")
        @Schema(name = "stock", example = "25")
        Integer stock
) {
}
