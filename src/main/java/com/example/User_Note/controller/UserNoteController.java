package com.example.User_Note.controller;

import com.example.User_Note.exception.EmptyFieldException;
import com.example.User_Note.exception.NoteIdNotFoundException;
import com.example.User_Note.model.UserNotes;
import com.example.User_Note.service.UserNoteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/user-note")
public class UserNoteController {
    private final UserNoteService userNoteService;
    public UserNoteController(UserNoteService userNoteService)
    {
        this.userNoteService = userNoteService;
    }

    @GetMapping("/home")
    public ResponseEntity<List<UserNotes>> getAllUserNotes()
    {
        return ResponseEntity.status(HttpStatus.OK).body(userNoteService.getAllUserNotes());
    }

    @GetMapping("/home/{id}")
    public ResponseEntity<?> getUserNotesById(@PathVariable int id) throws NoteIdNotFoundException
    {
        return ResponseEntity.status(HttpStatus.OK).body(userNoteService.getUserNotesById(id));
    }

    @PostMapping("/home")
    public ResponseEntity<String> addUserNote(@RequestBody UserNotes note) throws EmptyFieldException
    {
        userNoteService.addUserNote(note);
        return ResponseEntity.status(HttpStatus.CREATED).body("added the note");
    }

    @PutMapping("/home/{id}")
    public ResponseEntity<String> updateUserNoteById(@PathVariable int id, @RequestBody UserNotes note) throws NoteIdNotFoundException, EmptyFieldException
    {
        userNoteService.updateUserNoteById(id,note);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("updated the note");
    }

    @DeleteMapping("/home/{id}")
    public ResponseEntity<String> deleteUserNoteById(@PathVariable int id) throws NoteIdNotFoundException
    {
        userNoteService.deleteUserNoteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("deleted the note");
    }


}
