package com.example.User_Note.exception;

public class UsernameExistsException extends Throwable
{
    public UsernameExistsException()
    {
        super("** Username already exists **");
    }
}
