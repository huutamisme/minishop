package com.htam25.minishop.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductRequest {

    @NotBlank(message = "cannot be empty")
    String name;
    String description;

    @Min(value = 0, message = "cannot less than 0")
    Double price;

    @Min(value = 1, message = "cannot less than 1")
    Integer stock;

    @NotNull(message = "cannot be null")
    Long categoryId;

}
