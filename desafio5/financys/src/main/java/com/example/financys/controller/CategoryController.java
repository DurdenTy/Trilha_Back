package com.example.financys.controller;

import com.example.financys.entity.Category;
import com.example.financys.repository.CategoryRepository;
import com.example.financys.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;


    @PostMapping("/categorias")
    public Long create(@RequestBody Category category){

        CategoryService categoryService = new CategoryService();

        if(categoryService.validateCategoryById(categoryRepository, category)){
            System.out.println( "Id: " + category.getId() + " já existente no banco de dados");
            return -1L;
        }

        System.out.println( "Nova categoria com ID: " + category.getId() + " criada!");
        return categoryRepository.save(category).getId();

    }

    @GetMapping("/categorias")
    public List<Category> read(){

        return categoryRepository.findAll();

    }

    @GetMapping("/categorias/{id}")
    public Optional<Category> findCategoryById(Long id){

        System.out.println("Categoria identificada");
        return categoryRepository.findById(id);

    }

    @PutMapping("/categorias/")
    public ResponseEntity<Category> update(@RequestBody Category category, @PathVariable("id") Long id) throws IllegalStateException{

        Category category2 = categoryRepository.findById(id).orElseThrow(()
                -> new IllegalStateException(
                "Categoria com o id: " + id + " não encontrada"
        ));

        category2.setId(category.getId());
        category2.setName(category.getName());
        category2.setDescription(category.getDescription());
        categoryRepository.save(category2);
        return ResponseEntity.ok().body(category2);


    }

    @DeleteMapping("/categorias/{id}")
    public void delete(Long id){
        categoryRepository.deleteById(id);
    }

}

