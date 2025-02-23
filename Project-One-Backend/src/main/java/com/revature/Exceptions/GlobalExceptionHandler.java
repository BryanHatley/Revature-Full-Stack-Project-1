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

    // Handle UnauthorizedUser exception
    @ExceptionHandler(UnauthorizedUserException.class)
    public ResponseEntity<String> handleUnauthorizedUserException(UnauthorizedUserException e)
    {
        return ResponseEntity.status(401).body(e.getMessage());
    }

    // Handle any other exceptions
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<String> handleUncheckedException(Exception e)
//    {
//        return ResponseEntity.internalServerError().body("Oops, something went wrong try again later.");
//    }
}
