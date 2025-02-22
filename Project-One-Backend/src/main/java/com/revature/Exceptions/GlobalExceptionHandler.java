package com.revature.Exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler
{
    // Handle illegal argument exception
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e)
    {
        return ResponseEntity.badRequest().body(e.getMessage());
    }


    // Handle any other exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleUncheckedException(Exception e)
    {
        return ResponseEntity.internalServerError().body(e.getMessage());
    }
}
