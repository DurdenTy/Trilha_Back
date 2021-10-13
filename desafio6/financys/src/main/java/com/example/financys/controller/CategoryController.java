package com.example.financys.controller;

import com.example.financys.DTO.DTO_Chart;
import com.example.financys.entity.Category;
import com.example.financys.service.CategoryService;
import javassist.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService){

        this.categoryService = categoryService;

    }

    @PostMapping("/categorias")
    public Long create(@RequestBody Category category){

        return categoryService.save(category).getId();

    }

    @GetMapping("/categorias")
    public List<Category> read() throws NotFoundException {

        return categoryService.findAll();

    }

    @GetMapping("/categorias/{id}")
    public ResponseEntity<Category> findCategoryById(@PathVariable() Long id) throws NotFoundException {

        return categoryService.findById(id);

    }

    @PutMapping("/categorias/{id}")
    public ResponseEntity<Category> update(@RequestBody Category category, @PathVariable() Long id) throws NotFoundException {

        return categoryService.update(id, category);

    }

    @DeleteMapping("/categorias/{id}")
    public void delete(@PathVariable() Long id) throws NotFoundException {

        categoryService.deleteById(id);

    }


}

