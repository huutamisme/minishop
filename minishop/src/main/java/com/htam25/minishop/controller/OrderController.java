package com.htam25.minishop.controller;

import com.htam25.minishop.dto.response.OrderResponse;
import com.htam25.minishop.entity.Order;
import com.htam25.minishop.mapper.OrderMapper;
import com.htam25.minishop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final OrderMapper orderMapper;

    @PostMapping("/checkout")
    public OrderResponse checkout(@AuthenticationPrincipal UserDetails user) {
        return orderService.checkout(user.getUsername());
    }

    @GetMapping
    public Page<OrderResponse> getOrders (@RequestParam Long userId, Pageable pageable) {
        return orderService.getUserOrders(userId, pageable);
    }

}
