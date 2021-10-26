package com.example.Clean.UseCases.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptions {

    @ExceptionHandler(ExceptionCalculaMedia.class)
    public ResponseEntity<Object> calculaMediaErro(ExceptionCalculaMedia ex){

        ApiException apiException = new ApiException(ex.getMessage());

        return new ResponseEntity<>(apiException, HttpStatus.BAD_REQUEST);

    }

}
