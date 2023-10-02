package com.stephen.careTrack.handleExceptions;

public class HCWNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    public HCWNotFoundException(String message)
    {
        super(message);
    }
}
