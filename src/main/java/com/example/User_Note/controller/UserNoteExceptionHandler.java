package com.example.User_Note.controller;

import com.example.User_Note.exception.EmptyFieldException;
import com.example.User_Note.exception.NoteIdNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserNoteExceptionHandler
{
    @ExceptionHandler(EmptyFieldException.class)
    public ResponseEntity<String> handleEmptyNoteException(EmptyFieldException emptyFieldException)
    {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(emptyFieldException.getMessage());
    }

    @ExceptionHandler(NoteIdNotFoundException.class)
    public ResponseEntity<String> handleNoteIdNotFoundException(NoteIdNotFoundException noteIdNotFoundException)
    {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(noteIdNotFoundException.getMessage());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<String> handleAccessDeniedException()
    {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("** Access denied **");
    }
}
