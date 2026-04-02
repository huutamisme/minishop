package com.htam25.minishop.mapper;

import com.htam25.minishop.dto.response.CartItemResponse;
import com.htam25.minishop.entity.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CartMapper {

    @Mapping(source = "product.id", target = "productId")
    @Mapping(source = "product.name", target = "productName")
    @Mapping(source = "product.price", target = "price")
    CartItemResponse toDto(CartItem cartItem);

}
