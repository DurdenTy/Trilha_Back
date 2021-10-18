package com.example.financys.controller;

import com.example.financys.DTO.DTO_Category;
import com.example.financys.entity.Category;
import com.example.financys.service.CategoryService;
import javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService){

        this.categoryService = categoryService;

    }

    @PostMapping("/categorias")
    public Long create(@RequestBody @Valid DTO_Category dto_category){

        return categoryService.save(dto_category).getId();

    }

    public List<Category> read() throws NotFoundException {

        return categoryService.findAll();

    }

    @GetMapping("/categorias/{id}")
    public ResponseEntity<Category> findCategoryById(@PathVariable Long id) throws NotFoundException {

        return categoryService.findById(id);

    }

    @PutMapping("/categorias/{id}")
    public ResponseEntity<Category> update(@RequestBody @Valid Category category, @PathVariable Long id) throws NotFoundException {

        return categoryService.update(id, category);

    }

    @DeleteMapping("/categorias/{id}")
    public void delete(@PathVariable Long id) throws NotFoundException {

        categoryService.deleteById(id);

    }

    @GetMapping("/categorias")
    public List<DTO_Category> returnDTOCategory(){
        return categoryService.retornarListaDTO();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> errorHandler(MethodArgumentNotValidException ex){
        return categoryService.handleValidException(ex);
    }


}

