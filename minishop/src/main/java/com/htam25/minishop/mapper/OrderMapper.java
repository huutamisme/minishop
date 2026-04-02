package com.htam25.minishop.mapper;

import com.htam25.minishop.dto.response.OrderItemResponse;
import com.htam25.minishop.dto.response.OrderResponse;
import com.htam25.minishop.entity.Order;
import com.htam25.minishop.entity.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(source = "status", target = "status")
    OrderResponse toDto(Order order);

    @Mapping(source = "product.id", target = "productId")
    @Mapping(source = "product.name", target = "productName")
    OrderItemResponse toDto(OrderItem item);
}
