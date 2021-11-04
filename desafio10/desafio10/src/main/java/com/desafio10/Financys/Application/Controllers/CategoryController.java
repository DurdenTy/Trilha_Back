package com.desafio10.Financys.Application.Controllers;

import com.desafio10.Financys.Domain.Entities.Category;
import com.desafio10.Financys.Domain.Services.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {

    private CategoryService categoryService;


    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @PostMapping("/categorias")
    public Category create(@RequestBody Category category){
        return categoryService.create(category);
    }

    @GetMapping("/categorias")
    public List<Category> read(){
        return categoryService.read();
    }

    @GetMapping("/categorias/{id}")
    public Category readById(@PathVariable Long id){
        return categoryService.findById(id);
    }

    @PutMapping("/categorias/{id}")
    public Category update(@RequestBody Category category, @PathVariable Long id){
        return categoryService.update(category, id);
    }

    @DeleteMapping("/categorias/{id}")
    public void delete(@PathVariable Long id){
        categoryService.delete(id);
    }
}
