package com.example.User_Note.exception;

public class InvalidCredentialsException extends Throwable
{
    public InvalidCredentialsException()
    {
        super("* Invalid username or password");
    }
}
