package com.example.ticketbookingapp.model;


public class Movie {
    private String name;
    private String id;

    public Movie() {

    }
    
    public Movie(String name) {
        this.name = name;
    }

    public Movie setName(String name) {
        this.name = name;

        return this;
    }

    public String getName() {
        return this.name;
    }

    public String getId() {
        return this.id;
    }

    public Movie setId(String id) {
        this.id = id;
        
        return this;
    }
}
