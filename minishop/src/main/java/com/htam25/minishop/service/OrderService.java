package com.htam25.minishop.service;

import com.htam25.minishop.dto.response.OrderResponse;
import com.htam25.minishop.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {

    OrderResponse checkout(String email);

    Page<OrderResponse> getUserOrders(Long userId, Pageable pageable);
}
