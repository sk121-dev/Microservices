package com.sk.userms.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{
    
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String,String>> handleGenericException(RuntimeException ex){
        Map<String,String> apiErrorResponse = new HashMap<>();
        apiErrorResponse.put("message","An unexpected error occurred: "+ex.getMessage());
        apiErrorResponse.put("timestamp", LocalDateTime.now().toString());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(apiErrorResponse);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String,String>> handleResourceNotFoundException(ResourceNotFoundException ex){
        Map<String,String> apiErrorResponse = new HashMap<>();
        apiErrorResponse.put("message", ex.getMessage());
        apiErrorResponse.put("timestamp", LocalDateTime.now().toString());

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(apiErrorResponse);
    }
}
