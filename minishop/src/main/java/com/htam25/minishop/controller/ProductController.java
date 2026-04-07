package com.htam25.minishop.controller;

import com.htam25.minishop.dto.response.ProductResponse;
import com.htam25.minishop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public Page<ProductResponse> getAll(Pageable pageable) {
        return productService.getAllProducts(pageable);
    }

    @GetMapping("/{id}")
    public ProductResponse getProductDetail(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductDetail(id)).getBody();
    }

    @GetMapping("/category/{id}")
    public Page<ProductResponse> getByCategory(@PathVariable Long id, Pageable pageable) {
        return productService.getByCategory(id, pageable);
    }

    @GetMapping("/search")
    public Page<ProductResponse> search(@RequestParam String keyword, Pageable pageable) {
        return productService.search(keyword, pageable);
    }
}
