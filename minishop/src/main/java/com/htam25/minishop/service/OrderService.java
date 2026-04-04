package com.htam25.minishop.service;

import com.htam25.minishop.dto.response.OrderResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {

    OrderResponse checkout();

    Page<OrderResponse> getUserOrders(Pageable pageable);
}
