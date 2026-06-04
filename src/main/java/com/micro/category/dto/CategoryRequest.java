package com.micro.category.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(name = "Category request payload")
public record CategoryRequest(
        @NotBlank(message = "Category name is required")
        @Schema(name = "name",example = "Electronics")
        String name,

        @Schema(name = "description",example = "Electronic products")
        String description
) {
}
