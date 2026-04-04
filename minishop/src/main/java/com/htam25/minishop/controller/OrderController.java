package com.htam25.minishop.controller;

import com.htam25.minishop.dto.response.OrderResponse;
import com.htam25.minishop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/checkout")
    public OrderResponse checkout() {
        return orderService.checkout();
    }

    @GetMapping
    public Page<OrderResponse> getOrders (Pageable pageable) {
        return orderService.getUserOrders(pageable);
    }

}
