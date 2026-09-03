package com.samyr.jobtracker.exception;

public class CompanyNotFoundException extends RuntimeException{
    public CompanyNotFoundException(int id){
        super("Company with ID: " + id + " not found");
    }

}
