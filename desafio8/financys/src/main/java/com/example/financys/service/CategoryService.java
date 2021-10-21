package com.example.financys.service;

import com.example.financys.DTO.DtoCategory;
import com.example.financys.entity.Category;
import com.example.financys.repository.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import javax.management.relation.InvalidRelationIdException;
import javax.persistence.EntityNotFoundException;
import java.util.*;


@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    private List<DtoCategory> dtoCategoryList = new ArrayList<>();


    public CategoryService(CategoryRepository categoryRepository, ModelMapper modelMapper){

        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;

    }
    public boolean validateCategoryById(Category category){
        for (Category value : categoryRepository.findAll()) {
            if(value.getId() == category.getId()){
                return true;
            }
        }
        return false;
    }

    public Category save(DtoCategory dtoCategory) {

        Category category = mapToCategory(dtoCategory);

        if(validateCategoryById(category)){
            System.out.println( "Id: " + category.getId() + " já existente no banco de dados");
        }

        System.out.println( "Nova categoria com ID: " + category.getId() + " criada!");
        return categoryRepository.save(category);

    }

    public List<Category> findAll() {

        System.out.println("Categoria identificada");
        return categoryRepository.findAll();

    }

    public ResponseEntity<Category> findById(Long id) {

        Category category = categoryRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(
                        "Categoria com o id: " + id + " não encontrada"
                ));

        System.out.println("Categoria identificada");
        return ResponseEntity.ok().body(category);

    }

    public ResponseEntity<Category> update(Long id, Category category) {

        Category category2 = categoryRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(
                        "Categoria com o id: " + id + " não encontrada"
                ));

        category2 = category;
        category2.setId(category.getId());
        categoryRepository.save(category2);

        System.out.println("Categoria identificada");

        return ResponseEntity.ok().body(category2);
    }

    public Category mapToCategory(DtoCategory dtoCategory){
        Category category = modelMapper.map(dtoCategory, Category.class);
        return category;
    }

    public DtoCategory mapToDTO_Category(Category category){
        DtoCategory dtoCategory = modelMapper.map(category, DtoCategory.class);
        return dtoCategory;
    }

    public void deleteById(Long id) throws EntityNotFoundException {

        categoryRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(
                        "Categoria com o id: " + id + " não encontrada"
                ));

        categoryRepository.deleteById(id);

    }

    public List<DtoCategory> retornarListaDTO(){

        dtoCategoryList.clear();

        categoryRepository.findAll().stream().forEach(c-> dtoCategoryList.add(mapToDTO_Category(c)));

        return dtoCategoryList;
    }


}
