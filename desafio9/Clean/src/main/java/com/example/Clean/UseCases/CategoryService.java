package com.example.Clean.UseCases;

import com.example.Clean.Entities.ObjectsRules.ObjectCategory;
import com.example.Clean.Entities.Entities.Category;
import com.example.Clean.UseCases.Repositories.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import javax.persistence.EntityNotFoundException;
import java.util.*;


@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    private List<ObjectCategory> objectCategoryList = new ArrayList<>();


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

    public Category save(ObjectCategory objectCategory) {

        Category category = mapToCategory(objectCategory);

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

    public Category mapToCategory(ObjectCategory objectCategory){
        Category category = modelMapper.map(objectCategory, Category.class);
        return category;
    }

    public ObjectCategory mapToDTO_Category(Category category){
        ObjectCategory objectCategory = modelMapper.map(category, ObjectCategory.class);
        return objectCategory;
    }

    public void deleteById(Long id) throws EntityNotFoundException {

        categoryRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(
                        "Categoria com o id: " + id + " não encontrada"
                ));

        categoryRepository.deleteById(id);

    }

    public List<ObjectCategory> retornarListaDTO(){

        objectCategoryList.clear();

        categoryRepository.findAll().stream().forEach(c-> objectCategoryList.add(mapToDTO_Category(c)));

        return objectCategoryList;
    }


}
