package com.desafio10.Financys.Domain.Services;

import com.desafio10.Financys.Domain.Entities.Category;
import com.desafio10.Financys.Infrastruct.Repositories.CategoryRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class CategoryService {

    private CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    public Category create(Category category){

        if(validateById(category.getId())){
            return categoryRepository.save(category);
        }

        System.out.print( "Nova categoria com id já existente ");
        return category;
    }

    public List<Category> read(){
        return categoryRepository.findAll();
    }

    public Category findById(Long id){

        Category category = categoryRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(
                        "Categoria com o id: " + id + " não encontrada"
                ));

        System.out.println("Categoria identificada");
        return category;

    }

    public Category update(Category category, Long id){
        Category category2 = categoryRepository.findById(id).orElseThrow(()
                -> new IllegalStateException(
                "Categoria com o id: " + id + " não encontrada"
        ));

        category2.setId(category.getId());
        category2.setName(category.getName());
        category2.setDescription(category.getDescription());
        categoryRepository.save(category2);
        return category2;
    }

    public void delete(Long id){
        categoryRepository.deleteById(id);
    }

    public boolean validateById(Long id){
        Boolean bool = true;
        for(Category value : categoryRepository.findAll()) {
            if (value.equals(categoryRepository.findById(id))) {
                bool = false;
            }
        }
        return bool;

    }
}
