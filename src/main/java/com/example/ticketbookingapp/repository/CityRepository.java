package com.example.ticketbookingapp.repository;

import java.util.Map;

import com.example.ticketbookingapp.model.City;

public interface CityRepository {
    void addCity(City city) throws Exception;

    City findCity(String cityId);

    Map<String, City> getAllCities();
}
