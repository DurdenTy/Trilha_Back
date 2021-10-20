package com.example.financys.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import javax.management.relation.InvalidRelationIdException;
import javax.persistence.EntityNotFoundException;
import javax.validation.ValidationException;

@ControllerAdvice
public class CategoryExceptions {

    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<String> calculaMediaErro(ArithmeticException ex){
        return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<String> erroValidacaoDeEntidades(ValidationException ex){

        return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> entidadeNaoEncontrada(EntityNotFoundException ex){
        return new ResponseEntity(ex.getMessage(), HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(InvalidRelationIdException.class)
    public ResponseEntity<String> erroValidacaoDeId(InvalidRelationIdException ex){
        return new ResponseEntity(ex.getMessage(), HttpStatus.NO_CONTENT);
    }


}
