package com.micro.category.service.impl;

import com.micro.auth.dto.response.ApiResponse;
import com.micro.auth.dto.response.PagedResponse;
import com.micro.auth.exception.ApiException;
import com.micro.category.dto.CategoryRequest;
import com.micro.category.dto.CategoryResponse;
import com.micro.category.entity.Category;
import com.micro.category.repository.CategoryRepository;
import com.micro.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public ApiResponse<CategoryResponse> createCategory(CategoryRequest request) {
        if (categoryRepository.existsByNameIgnoreCase(request.name()))
            throw ApiException.conflict("Category exists");

        Category category = Category.builder()
                .name(request.name())
                .description(request.description())
                .build();
        category = categoryRepository.save(category);
        CategoryResponse response = mapToRes(category);

        return ApiResponse.success(
                "Category created successfully",
                response
        );
    }

    @Override
    public ApiResponse<CategoryResponse> updateCategory(Long id, CategoryRequest request) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> ApiException.notFound("Category not found"));

        if (!category.getName().equals(request.name()))
            throw ApiException.badRequest("Name can't be change");

        if (null != request.description())
            category.setDescription(request.description());

        category = categoryRepository.save(category);
        CategoryResponse response = mapToRes(category);
        return ApiResponse.success(
                "Category updated successfully",
                response
        );
    }

    @Override
    public ApiResponse<PagedResponse<CategoryResponse>> getCategories(int page, int size, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Category> categoryPage = categoryRepository.findAll(pageable);

        List<CategoryResponse> categories = categoryPage.getContent()
                .stream()
                .map(this::mapToRes)
                .toList();

        PagedResponse<CategoryResponse> response = PagedResponse.<CategoryResponse>builder()
                .content(categories)
                .page(categoryPage.getNumber())
                .size(categoryPage.getSize())
                .totalElements(categoryPage.getTotalElements())
                .totalPages(categoryPage.getTotalPages())
                .last(categoryPage.isLast())
                .build();

        return ApiResponse.success(
                "Categories fetched",
                response
        );
    }

    @Override
    public ApiResponse<CategoryResponse> getCategory(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> ApiException.notFound("Category not found"));
        CategoryResponse response = mapToRes(category);

        return ApiResponse.success(
                "Category fetched",
                response
        );
    }

    @Override
    public ApiResponse<CategoryResponse> deleteCategory(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> ApiException.notFound("Category not found"));
        categoryRepository.deleteById(id);
        CategoryResponse response = mapToRes(category);

        return ApiResponse.success(
                "Category deleted",
                response
        );
    }

    private CategoryResponse mapToRes(Category category) {
        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .createdAt(category.getCreatedAt())
                .updatedAt(category.getUpdatedAt())
                .build();
    }
}
