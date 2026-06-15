package com.micro.product.service.impl;

import com.micro.auth.dto.response.ApiResponse;
import com.micro.auth.dto.response.PagedResponse;
import com.micro.auth.exception.ApiException;
import com.micro.category.entity.Category;
import com.micro.category.repository.CategoryRepository;
import com.micro.product.dto.ProductRequest;
import com.micro.product.dto.ProductResponse;
import com.micro.product.entity.Product;
import com.micro.product.repository.ProductRepository;
import com.micro.product.service.ProductService;
import lombok.RequiredArgsConstructor;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public ApiResponse<ProductResponse> createProduct(ProductRequest request) {
        if (productRepository.findByName(request.name()).isPresent())
            throw ApiException.conflict("Product exists");

        Category category = categoryRepository.findById(request.categoryId())
                .orElseThrow(() -> ApiException.notFound("Category not found"));

        Product product = Product.builder()
                .name(request.name())
                .category(category)
                .description(request.description())
                .price(request.price())
                .stock(request.stock())
                .build();

        Product saved = productRepository.save(product);
        ProductResponse response = mapToProductRes(saved);
        return ApiResponse.success("Product create successful", response);
    }

    @Override
//    @CacheEvict(value = "products", key = "#id")
    public ApiResponse<ProductResponse> updateProduct(Long id, ProductRequest request) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> ApiException.notFound("Product not found"));

        Category category = categoryRepository.findById(request.categoryId())
                .orElseThrow(() -> ApiException.notFound("Category not found"));
        product.setCategory(category);

        if (null != request.name())
            product.setName(request.name());
        if (null != request.description())
            product.setDescription(request.description());
        if (null != request.stock())
            product.setStock(request.stock());
        if (null != request.price())
            product.setPrice(request.price());

        Product updatedProduct = productRepository.save(product);
        ProductResponse response = mapToProductRes(updatedProduct);
        return ApiResponse.success("Product updated successfully", response);
    }

    @Override
    public ApiResponse<PagedResponse<ProductResponse>> getProducts(int page, int size, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Product> productPage = productRepository.findAll(pageable);

        List<ProductResponse> products = productPage.getContent()
                .stream()
                .map(this::mapToProductRes)
                .toList();
        PagedResponse<ProductResponse> response = PagedResponse.<ProductResponse>builder()
                .content(products)
                .page(productPage.getNumber())
                .size(productPage.getSize())
                .totalElements(productPage.getTotalElements())
                .totalPages(productPage.getTotalPages())
                .last(productPage.isLast())
                .build();

        return ApiResponse.success(
                "Products fetched",
                response
        );
    }

    @Override
    public ApiResponse<PagedResponse<ProductResponse>> searchProducts(String keyword, int page, int size, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Product> productPage = productRepository.findByNameContainingIgnoreCase(keyword, pageable);

        List<ProductResponse> products = productPage.getContent()
                .stream()
                .map(this::mapToProductRes)
                .toList();
        PagedResponse<ProductResponse> response = PagedResponse.<ProductResponse>builder()
                .content(products)
                .page(productPage.getNumber())
                .size(productPage.getSize())
                .totalElements(productPage.getTotalElements())
                .totalPages(productPage.getTotalPages())
                .last(productPage.isLast())
                .build();

        return ApiResponse.success(
                "Products fetched",
                response
        );
    }

    @Override
    public ApiResponse<PagedResponse<ProductResponse>> filterProducts(BigDecimal minPrice, BigDecimal maxPrice, int page, int size, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Product> productPage = productRepository.findByPriceBetween(minPrice, maxPrice, pageable);

        List<ProductResponse> products = productPage.getContent()
                .stream()
                .map(this::mapToProductRes)
                .toList();
        PagedResponse<ProductResponse> response = PagedResponse.<ProductResponse>builder()
                .content(products)
                .page(productPage.getNumber())
                .size(productPage.getSize())
                .totalElements(productPage.getTotalElements())
                .totalPages(productPage.getTotalPages())
                .last(productPage.isLast())
                .build();

        return ApiResponse.success(
                "Products fetched",
                response
        );
    }

    @Override
//    @Cacheable(value = "products", key = "#id")
    public ApiResponse<ProductResponse> getProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> ApiException.notFound("Product not found"));
        ProductResponse response = mapToProductRes(product);
        return ApiResponse.success("Product fetched", response);
    }

    @Override
//    @CacheEvict(value = "products", key = "#id")
    public ApiResponse<ProductResponse> deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> ApiException.notFound("Product not found"));
        productRepository.deleteById(id);
        ProductResponse response = mapToProductRes(product);
        return ApiResponse.success("Product deleted", response);
    }

    private ProductResponse mapToProductRes(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .categoryId(product.getCategory().getId())
                .categoryName(product.getCategory().getName())
                .price(product.getPrice())
                .stock(product.getStock())
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .build();
    }
}
