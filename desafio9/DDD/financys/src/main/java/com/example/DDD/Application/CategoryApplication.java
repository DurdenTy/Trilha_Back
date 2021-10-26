package com.example.DDD.Application;

import com.example.DDD.Domain.ValueObject.ObjectCategory;
import com.example.DDD.Domain.Domain.Category;
import com.example.DDD.Services.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class CategoryApplication {

    private final CategoryService categoryService;


    public CategoryApplication(CategoryService categoryService){

        this.categoryService = categoryService;

    }

    @PostMapping("/categorias")
    public Long create(@RequestBody @Valid ObjectCategory objectCategory) {

        return categoryService.save(objectCategory).getId();

    }

    public List<Category> read() {

        return categoryService.findAll();

    }

    @GetMapping("/categorias/{id}")
    public ResponseEntity<Category> findCategoryById(@PathVariable Long id) {

        return categoryService.findById(id);

    }

    @PutMapping("/categorias/{id}")
    public ResponseEntity<Category> update(@RequestBody @Valid Category category, @PathVariable Long id) {

        return categoryService.update(id, category);

    }

    @DeleteMapping("/categorias/{id}")
    public void delete(@PathVariable Long id) {

        categoryService.deleteById(id);

    }

    @GetMapping("/categorias")
    public List<ObjectCategory> returnDTOCategory(){
        return categoryService.retornarListaDTO();
    }


}

