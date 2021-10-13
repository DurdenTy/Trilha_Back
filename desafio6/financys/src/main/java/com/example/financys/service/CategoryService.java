package com.example.financys.service;

import com.example.financys.DTO.DTO_Chart;
import com.example.financys.entity.Category;
import com.example.financys.repository.CategoryRepository;
import javassist.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;


    public CategoryService(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    public boolean validateCategoryById(Category category){
        for (Category value : categoryRepository.findAll()) {
            if(value.getId() == category.getId()){
                return true;
            }
        }
        return false;
    }

    public Category save(Category category){

        if(validateCategoryById(category)){
            System.out.println( "Id: " + category.getId() + " já existente no banco de dados");
            return null;
        }

        System.out.println( "Nova categoria com ID: " + category.getId() + " criada!");
        return categoryRepository.save(category);

    }

    public List<Category> findAll() throws NotFoundException {

        System.out.println("Categoria identificada");
        return categoryRepository.findAll();

    }

    public ResponseEntity<Category> findById(Long id) throws NotFoundException {

        Category category = categoryRepository.findById(id).orElseThrow(() ->
                new NotFoundException(
                        "Categoria com o id: " + id + " não encontrada"
                ));

        System.out.println("Categoria identificada");
        return ResponseEntity.ok().body(category);

    }

    public ResponseEntity<Category> update(Long id, Category category) throws NotFoundException{

        Category category2 = categoryRepository.findById(id).orElseThrow(() ->
                new NotFoundException(
                        "Categoria com o id: " + id + " não encontrada"
                ));

        category2.setId(category.getId());
        category2.setName(category.getName());
        category2.setDescription(category.getDescription());
        categoryRepository.save(category2);

        System.out.println("Categoria identificada");

        return ResponseEntity.ok().body(category2);
    }

    public void deleteById(Long id) throws NotFoundException{

        categoryRepository.findById(id).orElseThrow(() ->
                new NotFoundException(
                        "Categoria com o id: " + id + " não encontrada"
                ));

        categoryRepository.deleteById(id);

    }

}
