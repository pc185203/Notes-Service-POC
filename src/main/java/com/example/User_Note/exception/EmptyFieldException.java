package com.example.User_Note.exception;

public class EmptyFieldException extends Throwable
{
    public EmptyFieldException(String field)
    {
        super("** "+field+" can't be empty **");
    }
}
