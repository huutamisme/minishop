package com.htam25.minishop.dto.response;

import lombok.Data;

@Data
public class OrderItemResponse {
    private Long productId;
    private String productName;
    private Double price;
    private int quantity;
}
