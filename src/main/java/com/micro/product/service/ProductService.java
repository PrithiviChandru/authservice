package com.micro.product.service;

import com.micro.auth.dto.response.ApiResponse;
import com.micro.auth.dto.response.PagedResponse;
import com.micro.product.dto.ProductRequest;
import com.micro.product.dto.ProductResponse;
import com.micro.product.entity.Product;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    ApiResponse<ProductResponse> createProduct(ProductRequest request);

    ApiResponse<ProductResponse> updateProduct(Long id, ProductRequest request);

    ApiResponse<PagedResponse<ProductResponse>> getProducts(int page, int size, String sortBy, String sortDir);

    ApiResponse<PagedResponse<ProductResponse>> searchProducts(String keyword, int page, int size, String sortBy, String sortDir);

    ApiResponse<PagedResponse<ProductResponse>> filterProducts(BigDecimal minPrice, BigDecimal maxPrice, int page, int size, String sortBy, String sortDir);

    ApiResponse<ProductResponse> getProduct(Long id);

    ApiResponse<ProductResponse> deleteProduct(Long id);
}
