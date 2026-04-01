package com.htam25.minishop.service;

import com.htam25.minishop.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ProductService {

    Page<Product> getAllProducts(Pageable pageable);

    Page<Product> getByCategory(Long categoryId, Pageable pageable);

    Page<Product> search(String keyword, Pageable pageable);
}
