package com.example.Clean.UseCases.Repositories;

import com.example.Clean.Entities.Entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CategoryRepository extends JpaRepository<Category, Long> {
}
