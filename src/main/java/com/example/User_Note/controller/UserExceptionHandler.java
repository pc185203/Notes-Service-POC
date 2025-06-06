package com.example.User_Note.controller;

import com.example.User_Note.exception.InvalidCredentialsException;
import com.example.User_Note.exception.UsernameExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserExceptionHandler
{
    @ExceptionHandler(UsernameExistsException.class)
    public ResponseEntity<String> usernameExistsExceptionHandler(UsernameExistsException usernameExistsException)
    {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(usernameExistsException.getMessage());
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<String> invalidCredentialsExceptionHandler(InvalidCredentialsException invalidCredentialsException)
    {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(invalidCredentialsException.getMessage());
    }

}
