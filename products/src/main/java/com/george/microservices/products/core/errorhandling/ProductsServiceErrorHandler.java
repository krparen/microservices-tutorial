package com.george.microservices.products.core.errorhandling;


import org.axonframework.commandhandling.CommandExecutionException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@RestControllerAdvice
public class ProductsServiceErrorHandler {

    @ExceptionHandler(value = IllegalStateException.class)
    public ResponseEntity<ErrorMessage> handleIllegalStateException(IllegalStateException ex, WebRequest webRequest) {
        return new ResponseEntity<>(new ErrorMessage(new Date(), ex.getMessage()), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = CommandExecutionException.class)
    public ResponseEntity<?> handleCommandExecutionException(CommandExecutionException ex, WebRequest webRequest) {
        return new ResponseEntity<>(new ErrorMessage(new Date(),"comm exec ex: " + ex.getMessage()), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<?> handleOtherException(Exception ex, WebRequest webRequest) {
        return new ResponseEntity<>(new ErrorMessage(new Date(), ex.getMessage()), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
