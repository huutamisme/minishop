package com.htam25.minishop.repository;

import com.htam25.minishop.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Locale;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
