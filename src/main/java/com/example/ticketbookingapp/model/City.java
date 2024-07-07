package com.example.ticketbookingapp.model;

public class City {
    private String name;
    private String id;

    public City() {

    }

    public City(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public City setName(String name) {
        this.name = name;

        return this;
    }

    public City setId(String id) {
        this.id = id;

        return this;
    }

    public String getId() {
        return this.id;
    }
}
