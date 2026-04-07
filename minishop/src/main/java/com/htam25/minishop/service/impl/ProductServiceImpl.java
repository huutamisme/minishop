package com.htam25.minishop.service.impl;

import com.htam25.minishop.dto.response.ProductResponse;
import com.htam25.minishop.entity.Product;
import com.htam25.minishop.mapper.ProductMapper;
import com.htam25.minishop.repository.CategoryRepository;
import com.htam25.minishop.repository.ProductRepository;
import com.htam25.minishop.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryRepository categoryRepository;

    @Override
    public Page<ProductResponse> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable)
                .map(productMapper::toDto);
    }

    @Override
    public ProductResponse getProductDetail(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        String categoryName = categoryRepository.findById(product.getId()).get().getName();

        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .categoryName(categoryName)
                .description(product.getDescription())
                .price(product.getPrice())
                .stock(product.getStock())
                .build();
    }

    @Override
    public Page<ProductResponse> getByCategory(Long categoryId, Pageable pageable) {
        return productRepository.findByCategoryId(categoryId, pageable)
                .map(productMapper::toDto);
    }

    @Override
    public Page<ProductResponse> search(String keyword, Pageable pageable) {
        return productRepository.findByNameContainingIgnoreCase(keyword, pageable)
                .map(productMapper::toDto);
    }
}
