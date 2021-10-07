package com.example.financys.service;

import com.example.financys.entity.Category;
import com.example.financys.entity.Entry;
import com.example.financys.repository.CategoryRepository;

public class CategoryService {

    public boolean validateCategoryById(CategoryRepository categoryRepository, Category category){
        for (Category value : categoryRepository.findAll()) {
            if(value.getId() == category.getId()){
                return true;
            }
        }
        return false;
    }
}
