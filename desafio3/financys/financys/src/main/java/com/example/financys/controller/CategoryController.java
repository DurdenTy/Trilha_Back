package com.example.financys.controller;

import com.example.financys.category.Category;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CategoryController {

    List<Category> lista = new ArrayList<Category>();


    @PostMapping("/categorias")
    public int create(@RequestBody Category category){

        lista.add(category);
        return lista.indexOf(category);

    }

    @GetMapping("/categorias")
    public List<Category> read(){

        return lista;
    }
}

