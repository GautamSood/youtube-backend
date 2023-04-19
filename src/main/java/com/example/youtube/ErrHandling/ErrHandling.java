package com.example.youtube.ErrHandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ErrHandling {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String ,String> AuthException(MethodArgumentNotValidException ex){
        Map< String,String > errors = new HashMap<>();
        ex.getBindingResult()
                .getFieldErrors()
                .forEach(error -> {
                    errors.put(error.getField(), error.getDefaultMessage());
                });
        return errors;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String ,String>> handleException(Exception ex) {
        String message = ex.getMessage();
        Map< String,String > errors = new HashMap<>();
        errors.put("error",message);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errors);
    }
}
