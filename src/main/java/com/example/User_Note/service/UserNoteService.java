package com.example.User_Note.service;

import com.example.User_Note.exception.EmptyFieldException;
import com.example.User_Note.exception.NoteIdNotFoundException;
import com.example.User_Note.model.UserNotes;
import com.example.User_Note.repository.UserNoteRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserNoteService {
    private final UserNoteRepository userNoteRepository;
    public UserNoteService(UserNoteRepository userNoteRepository)
    {
        this.userNoteRepository = userNoteRepository;
    }

    public List<UserNotes> getAllUserNotes()
    {
        return userNoteRepository.findAll();
    }

    public UserNotes getUserNotesById(int id) throws NoteIdNotFoundException
    {
        UserNotes userNotes = userNoteRepository.findById(id).orElse(null);
        if(userNotes == null)
        {
            throw new NoteIdNotFoundException();
        }
        return userNotes;
    }

    public void addUserNote(UserNotes note) throws EmptyFieldException
    {
        if(note.getNote().isEmpty())
        {
            throw new EmptyFieldException("note");
        }
        userNoteRepository.save(note);
    }

    public void updateUserNoteById(int id,UserNotes note) throws EmptyFieldException, NoteIdNotFoundException
    {
        UserNotes userNotes = userNoteRepository.findById(id).orElse(null);
        if(userNotes == null)
        {
            throw new NoteIdNotFoundException();
        }
        if(note.getNote().isEmpty())
        {
            throw new EmptyFieldException("note");
        }
        userNotes.setNote(note.getNote());
        userNotes.setLast_edited(note.getLast_edited());
        userNoteRepository.save(userNotes);
    }

    public void deleteUserNoteById(int id) throws NoteIdNotFoundException
    {
        UserNotes userNotes = userNoteRepository.findById(id).orElse(null);
        if(userNotes == null)
        {
            throw new NoteIdNotFoundException();
        }
        userNoteRepository.deleteById(id);
    }
}
