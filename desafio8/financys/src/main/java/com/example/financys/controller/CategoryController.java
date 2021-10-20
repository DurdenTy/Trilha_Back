package com.example.financys.controller;

import com.example.financys.DTO.DtoCategory;
import com.example.financys.entity.Category;
import com.example.financys.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.InvalidRelationIdException;
import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;

@RestController
public class CategoryController {

    private final CategoryService categoryService;


    public CategoryController(CategoryService categoryService){

        this.categoryService = categoryService;

    }

    @PostMapping("/categorias")
    public Long create(@RequestBody @Valid DtoCategory dtoCategory) throws InvalidRelationIdException {

        return categoryService.save(dtoCategory).getId();

    }

    public List<Category> read() {

        return categoryService.findAll();

    }

    @GetMapping("/categorias/{id}")
    public ResponseEntity<Category> findCategoryById(@PathVariable Long id) throws EntityNotFoundException {

        return categoryService.findById(id);

    }

    @PutMapping("/categorias/{id}")
    public ResponseEntity<Category> update(@RequestBody @Valid Category category, @PathVariable Long id) throws EntityNotFoundException {

        return categoryService.update(id, category);

    }

    @DeleteMapping("/categorias/{id}")
    public void delete(@PathVariable Long id) throws EntityNotFoundException {

        categoryService.deleteById(id);

    }

    @GetMapping("/categorias")
    public List<DtoCategory> returnDTOCategory(){
        return categoryService.retornarListaDTO();
    }


}

