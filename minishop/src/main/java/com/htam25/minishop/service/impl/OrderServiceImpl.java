package com.htam25.minishop.service.impl;

import com.htam25.minishop.dto.response.OrderItemResponse;
import com.htam25.minishop.dto.response.OrderResponse;
import com.htam25.minishop.entity.*;
import com.htam25.minishop.mapper.OrderMapper;
import com.htam25.minishop.repository.*;
import com.htam25.minishop.security.user.CurrentUserService;
import com.htam25.minishop.service.OrderService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;
    private final OrderItemRepository orderItemRepository;
    private final OrderMapper orderMapper;
    private final CurrentUserService currentUserService;
    @Override
    @Transactional
    public OrderResponse checkout(){

        User user = currentUserService.getCurrentUser();

        List<CartItem> cartItems = cartItemRepository.findByUser_Id(user.getId());

        if(cartItems.isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }

        Order order = new Order();
        order.setUser(user);
        order.setStatus(OrderStatus.PENDING);
        order.setTotalPrice(0.0);
        order = orderRepository.save(order);

        double total = 0;

        for (CartItem item : cartItems) {

            Product product = item.getProduct();

            if(item.getQuantity() > product.getStock()) {
                throw new RuntimeException("Not enough stock for product: " + product.getName());
            }

            product.setStock(product.getStock() - item.getQuantity());

            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(product);
            orderItem.setQuantity(item.getQuantity());
            orderItem.setPrice(product.getPrice());

            total += product.getPrice() * item.getQuantity();
            orderItemRepository.save(orderItem);
        }

        order.setTotalPrice(total);
        orderRepository.save(order);

        List<OrderItem> orderItems = orderItemRepository.findByOrder_Id(order.getId());

        List<OrderItemResponse> itemResponses = orderItems.stream()
                .map(item -> {
                    OrderItemResponse dto = new OrderItemResponse();
                    dto.setProductId(item.getProduct().getId());
                    dto.setProductName(item.getProduct().getName());
                    dto.setPrice(item.getPrice());
                    dto.setQuantity(item.getQuantity());
                    return dto;
                })
                .toList();

        OrderResponse response = new OrderResponse();
        response.setId(order.getId());
        response.setTotalPrice(order.getTotalPrice());
        response.setStatus(order.getStatus().name());
        response.setItems(itemResponses);

        cartItemRepository.deleteByUser_Id(user.getId());

        return response;
    }

    @Override
    public Page<OrderResponse> getUserOrders(Pageable pageable) {
        Long userId = currentUserService.getUserId();
        Page<Order> orders = orderRepository.findByUserId(userId, pageable);
        return orders.map(orderMapper::toDto);
    }
}
