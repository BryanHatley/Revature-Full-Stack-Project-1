package com.revature.Exceptions;

public class UnauthorizedUserException extends RuntimeException
{
    public UnauthorizedUserException(String message)
    {
        super(message);
    }
}
