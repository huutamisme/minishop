package com.htam25.minishop.repository;

import com.htam25.minishop.entity.Product;
import jakarta.persistence.LockModeType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;


public interface ProductRepository extends JpaRepository<Product, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT p FROM Product p WHERE p.id = :id")
    Product findByIdForUpdate(Long id);

    Page<Product> findByDeletedFalse(Pageable pageable);

    Page<Product> findByCategoryIdAndDeletedFalse(Long categoryId, Pageable pageable);

    Page<Product> findByNameContainingIgnoreCaseAndDeletedFalse(String keyword, Pageable pageable);
}
