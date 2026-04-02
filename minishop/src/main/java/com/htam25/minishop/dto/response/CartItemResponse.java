package com.htam25.minishop.dto.response;

import lombok.Data;

@Data
public class CartItemResponse {
    private Long productId;
    private String productName;
    private Double price;
    private int quantity;

}
