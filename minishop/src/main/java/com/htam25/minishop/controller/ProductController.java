package com.htam25.minishop.controller;

import com.htam25.minishop.dto.response.ProductResponse;
import com.htam25.minishop.mapper.ProductMapper;
import com.htam25.minishop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    @GetMapping
    public Page<ProductResponse> getAll(Pageable pageable) {
        return productService.getAllProducts(pageable)
                .map(productMapper::toDto);
    }

    @GetMapping("/search")
    public Page<ProductResponse> search(@RequestParam String keyword, Pageable pageable) {
        return productService.search(keyword, pageable)
                .map(productMapper::toDto);
    }
}
