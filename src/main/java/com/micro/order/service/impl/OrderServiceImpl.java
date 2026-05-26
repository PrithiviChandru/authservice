package com.micro.order.service.impl;

import com.micro.auth.dto.response.ApiResponse;
import com.micro.auth.entity.User;
import com.micro.auth.exception.ApiException;
import com.micro.order.dto.OrderRequest;
import com.micro.order.dto.OrderResponse;
import com.micro.order.entity.Order;
import com.micro.order.enums.OrderStatus;
import com.micro.order.repository.OrderRepository;
import com.micro.order.service.OrderService;
import com.micro.product.entity.Product;
import com.micro.product.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    @Override
    @Transactional
    public ApiResponse<OrderResponse> createOrder(Authentication authentication, OrderRequest request) {
        User user = (User) authentication.getPrincipal();
        Product product = productRepository.findById(request.productId())
                .orElseThrow(() -> ApiException.notFound("Product not found"));

        if (product.getStock() < request.quantity())
            throw ApiException.badRequest("Insufficient stock available");

        BigDecimal totalAmount = product.getPrice().multiply(BigDecimal.valueOf(request.quantity()));

        product.setStock(product.getStock() - request.quantity());

        Order order = Order.builder()
                .user(user)
                .product(product)
                .quantity(request.quantity())
                .price(product.getPrice())
                .totalAmount(totalAmount)
                .status(OrderStatus.CREATED)
                .build();

        productRepository.save(product);
        Order savedOrder = orderRepository.save(order);
        OrderResponse response = mapToOrderResponse(savedOrder);

        return ApiResponse.success(
                "Order created successfully",
                response
        );
    }

    @Override
    public ApiResponse<List<OrderResponse>> myOrders(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        List<Order> orders = orderRepository.findByUserId(user.getId());
        List<OrderResponse> responses = orders.stream()
                .map(order -> mapToOrderResponse(order))
                .collect(Collectors.toUnmodifiableList());

        return ApiResponse.success(
                "Orders fetched successfully",
                responses
        );
    }

    @Override
    public ApiResponse<List<OrderResponse>> getOrders() {
        List<OrderResponse> responses = orderRepository.findAll().stream()
                .map(order -> mapToOrderResponse(order))
                .collect(Collectors.toUnmodifiableList());

        return ApiResponse.success(
                "Orders fetched successfully",
                responses
        );
    }

    @Override
    public ApiResponse<OrderResponse> getOrder(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> ApiException.notFound("Order not found"));
        OrderResponse response = mapToOrderResponse(order);

        return ApiResponse.success(
                "Order fetched successfully",
                response
        );
    }

    @Override
    @Transactional
    public ApiResponse<OrderResponse> cancelOrder(Authentication authentication, Long id) {
        User user = (User) authentication.getPrincipal();
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> ApiException.notFound("Order not found"));

        // USER can cancel only own orders
        boolean isAdmin = user.getRole().name().equals("ADMIN");
        if (!isAdmin && !order.getUser().getId().equals(user.getId()))
            throw ApiException.badRequest("You are not allowed to cancel this order");

        // only CREATED orders can be cancelled
        if (order.getStatus() != OrderStatus.CREATED)
            throw ApiException.badRequest("Only created orders can be cancelled");

        // update status
        order.setStatus(OrderStatus.CANCELLED);
        // restore stock
        Product product = order.getProduct();
        product.setStock(product.getStock() + order.getQuantity());

        productRepository.save(product);
        Order updatedOrder = orderRepository.save(order);
        OrderResponse response = mapToOrderResponse(updatedOrder);

        return ApiResponse.success(
                "Order cancelled successfully",
                response
        );
    }

    private OrderResponse mapToOrderResponse(Order order) {
        User user = order.getUser();
        Product product = order.getProduct();
        return OrderResponse.builder()
                .orderId(order.getId())
                .userId(user.getId())
                .userName(user.getFirstName() + " " + user.getLastName())
                .productId(product.getId())
                .productName(product.getName())
                .quantity(order.getQuantity())
                .price(order.getPrice())
                .totalAmount(order.getTotalAmount())
                .status(order.getStatus())
                .createdAt(order.getCreatedAt())
                .build();
    }
}
