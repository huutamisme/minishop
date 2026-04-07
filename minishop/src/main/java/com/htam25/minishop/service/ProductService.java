package com.htam25.minishop.service;

import com.htam25.minishop.dto.request.ProductRequest;
import com.htam25.minishop.dto.response.ProductResponse;
import com.htam25.minishop.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;


public interface ProductService {

    Page<ProductResponse> getAllProducts(Pageable pageable);

    Page<ProductResponse> getByCategory(Long categoryId, Pageable pageable);

    ProductResponse getProductDetail(Long id);

    Page<ProductResponse> search(String keyword, Pageable pageable);

    ProductResponse create(ProductRequest request);

    ProductResponse update(Long id, ProductRequest request);

    void delete(Long id);
}
