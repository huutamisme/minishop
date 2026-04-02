package com.htam25.minishop.dto.response;

import lombok.Data;

@Data
public class ProductResponse {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private String categoryName;
    private Integer stock;
}
