package com.samyr.jobtracker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ApplicationNotFoundException.class)
    public ResponseEntity<String> handlerApplicationNotFound(ApplicationNotFoundException anfe){
        return new ResponseEntity<>(anfe.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CompanyNotFoundException.class)
    public ResponseEntity<String> handlerCompanyNotFound(CompanyNotFoundException cnfe){
        return new ResponseEntity<>(cnfe.getMessage(), HttpStatus.NOT_FOUND);
    }
}
