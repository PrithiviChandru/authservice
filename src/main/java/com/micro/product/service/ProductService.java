package com.micro.product.service;

import com.micro.auth.dto.response.ApiResponse;
import com.micro.product.dto.ProductRequest;
import com.micro.product.dto.ProductResponse;

import java.util.List;

public interface ProductService {
    ApiResponse<ProductResponse> createProduct(ProductRequest request);

    ApiResponse<ProductResponse> updateProduct(Long id, ProductRequest request);

    ApiResponse<List<ProductResponse>> getProducts();

    ApiResponse<ProductResponse> getProduct(Long id);

    ApiResponse<ProductResponse> deleteProduct(Long id);
}
