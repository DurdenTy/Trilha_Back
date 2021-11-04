package com.desafio10.Financys.Application.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptions {


    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ExceptionHandler(NoContentException.class)
    public ResponseEntity<Object> erroNoContent(NoContentException ex){

        ApiException apiException = new ApiException(ex.getMessage());

        return new ResponseEntity<>(apiException, HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(ParametrosNulos.class)
    public ResponseEntity<Object> erroParametrosNulos(ParametrosNulos ex){
        ApiException apiException = new ApiException(ex.getMessage());

        return new ResponseEntity<>(apiException, HttpStatus.BAD_REQUEST);
    }



}
