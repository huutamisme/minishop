package com.htam25.minishop.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class OrderResponse {
    private Long id;
    private Double totalPrice;
    private String status;
    private List<OrderItemResponse> items;
}
