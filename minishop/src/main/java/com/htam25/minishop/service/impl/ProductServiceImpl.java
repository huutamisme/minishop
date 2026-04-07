package com.htam25.minishop.service.impl;

import com.htam25.minishop.dto.request.ProductRequest;
import com.htam25.minishop.dto.response.ProductResponse;
import com.htam25.minishop.entity.Category;
import com.htam25.minishop.entity.Product;
import com.htam25.minishop.mapper.ProductMapper;
import com.htam25.minishop.repository.CartItemRepository;
import com.htam25.minishop.repository.CategoryRepository;
import com.htam25.minishop.repository.ProductRepository;
import com.htam25.minishop.service.ProductService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryRepository categoryRepository;
    private final CartItemRepository cartItemRepository;

    @Override
    public Page<ProductResponse> getAllProducts(Pageable pageable) {
        return productRepository.findByDeletedFalse(pageable)
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
        return productRepository.findByCategoryIdAndDeletedFalse(categoryId, pageable)
                .map(productMapper::toDto);
    }

    @Override
    public Page<ProductResponse> search(String keyword, Pageable pageable) {
        return productRepository.findByNameContainingIgnoreCaseAndDeletedFalse(keyword, pageable)
                .map(productMapper::toDto);
    }

    @Override
    public ProductResponse create(ProductRequest request) {

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Product product = new Product();
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setStock(request.getStock());
        product.setCategory(category);

        productRepository.save(product);
        return productMapper.toDto(product);
    }

    @Override
    public ProductResponse update(Long id, ProductRequest request) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Category category = product.getCategory();

        if(!category.getId().equals(request.getCategoryId())) {
            category = categoryRepository.findById(request.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Category not found"));
        }


        product.setCategory(category);
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setStock(request.getStock());
        product.setDescription(request.getDescription());

        productRepository.save(product);

        return productMapper.toDto(product);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() ->  new RuntimeException("Product not found"));

        cartItemRepository.deleteByProduct_Id(product.getId());

        product.setDeleted(true);

        productRepository.save(product);
    }
}
