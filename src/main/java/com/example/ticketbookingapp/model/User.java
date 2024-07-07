package com.example.ticketbookingapp.model;

public class User {
    private String name;
    private String id;

    // for jackson lib
    public User() {

    }

    public User(String name) {
        this.name = name;
    }

    public User setName(String name) {
        this.name = name;

        return this;
    }

    public String getName() {
        return this.name;
    }

    public User setId(String id) {
        this.id = id;

        return this;
    }

    public String getId() {
        return this.id;
    }
}
