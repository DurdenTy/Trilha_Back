package com.example.financys.exception;

public class ExceptionCalculaMedia extends RuntimeException{

    public ExceptionCalculaMedia(String message){
        super(message);
    }

    public ExceptionCalculaMedia(String message, Throwable cause){
        super(message, cause);
    }

}
