package com.example.ticketbookingapp.exceptions;

public class MovieAlreadyExistsException extends Exception{
    public MovieAlreadyExistsException(String message) {
        super(message);
    }
}
