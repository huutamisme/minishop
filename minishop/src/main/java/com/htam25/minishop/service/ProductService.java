package com.htam25.minishop.service;

import com.htam25.minishop.dto.response.ProductResponse;
import com.htam25.minishop.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ProductService {

    Page<ProductResponse> getAllProducts(Pageable pageable);

    Page<ProductResponse> getByCategory(Long categoryId, Pageable pageable);

    ProductResponse getProductDetail(Long id);

    Page<ProductResponse> search(String keyword, Pageable pageable);
}
