package com.samyr.jobtracker.exception;

public class ApplicationNotFoundException extends RuntimeException{
    public ApplicationNotFoundException(int id){
        super("Application with ID: " + id + " not found");
    }
}
