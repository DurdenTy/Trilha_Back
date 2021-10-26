package com.example.DDD.Infrastructure.Repositories;

import com.example.DDD.Domain.Domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CategoryRepository extends JpaRepository<Category, Long> {
}
