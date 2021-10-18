package com.example.financys.service;

import com.example.financys.DTO.DTO_Category;
import com.example.financys.DTO.DTO_Chart;
import com.example.financys.entity.Category;
import com.example.financys.repository.CategoryRepository;
import javassist.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.*;


@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    private List<DTO_Category> DTO_CategoryList = new ArrayList<>();


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

    public Category save(DTO_Category dto_category){

        Category category = mapToCategory(dto_category);

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

        category2 = category;
        category2.setId(category.getId());
        categoryRepository.save(category2);

        System.out.println("Categoria identificada");

        return ResponseEntity.ok().body(category2);
    }

    public Category mapToCategory(DTO_Category dto_category){
        Category category = modelMapper.map(dto_category, Category.class);
        return category;
    }

    public DTO_Category mapToDTO_Category(Category category){
        DTO_Category dto_category = modelMapper.map(category, DTO_Category.class);
        return dto_category;
    }

    public void deleteById(Long id) throws NotFoundException{

        categoryRepository.findById(id).orElseThrow(() ->
                new NotFoundException(
                        "Categoria com o id: " + id + " não encontrada"
                ));

        categoryRepository.deleteById(id);

    }

    public List<DTO_Category> retornarListaDTO(){

        DTO_CategoryList.clear();

        for(Category category : categoryRepository.findAll()){
            DTO_CategoryList.add(mapToDTO_Category(category));
        }

        return DTO_CategoryList;
    }

    public Map<String, String> handleValidException(MethodArgumentNotValidException exception){
        Map<String, String> erros = new HashMap<>();

        exception.getBindingResult().getAllErrors().forEach((error) ->
            {
                String fieldName = ((FieldError)error).getField();
                String errorMessage = error.getDefaultMessage();

                erros.put(fieldName, errorMessage);
            });

        return erros;
    }
}
