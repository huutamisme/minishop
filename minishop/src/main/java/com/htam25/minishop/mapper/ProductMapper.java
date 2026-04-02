package com.htam25.minishop.mapper;

import com.htam25.minishop.dto.response.ProductResponse;
import com.htam25.minishop.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(source = "category.name", target = "categoryName")
    ProductResponse toDto(Product product);
}
