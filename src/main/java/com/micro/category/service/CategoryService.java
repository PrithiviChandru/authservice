package com.micro.category.service;

import com.micro.auth.dto.response.ApiResponse;
import com.micro.auth.dto.response.PagedResponse;
import com.micro.category.dto.CategoryRequest;
import com.micro.category.dto.CategoryResponse;

public interface CategoryService {
    ApiResponse<CategoryResponse> createCategory(CategoryRequest request);

    ApiResponse<CategoryResponse> updateCategory(Long id, CategoryRequest request);

    ApiResponse<PagedResponse<CategoryResponse>> getCategories(int page, int size, String sortBy, String sortDir);

    ApiResponse<CategoryResponse> getCategory(Long id);

    ApiResponse<CategoryResponse> deleteCategory(Long id);
}
