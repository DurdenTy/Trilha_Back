package com.example.Clean.UseCases.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApiException {

    private final String message;

}
