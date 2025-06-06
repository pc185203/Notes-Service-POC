package com.example.User_Note.exception;

public class NoteIdNotFoundException extends Throwable
{
    public NoteIdNotFoundException()
    {
        super("** Note with that id is not found **");
    }
}
