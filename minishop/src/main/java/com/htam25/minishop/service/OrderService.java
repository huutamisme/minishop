package com.htam25.minishop.service;

import com.htam25.minishop.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {

    Order checkout(Long userId);

    Page<Order> getUserOrders(Long userId, Pageable pageable);
}
