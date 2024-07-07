package com.example.ticketbookingapp.model;


public class Theatre {
    private String name;

    private String cityId;

    private String id;

    public Theatre() {

    }

    public Theatre(String name) {
        this.name = name;
    }

    public Theatre setName(String name) {
        this.name = name;

        return this;
    }

    public String getName() {
        return this.name;
    }

    public String getId() {
        return this.id;
    }

    public Theatre setId(String id) {
        this.id = id;

        return this;
    }

    public String getCityId() {
        return this.cityId;
    }

    public Theatre setCityId(String cityId) {
        this.cityId = cityId;

        return this;
    }
}
