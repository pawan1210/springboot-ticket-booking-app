package com.example.ticketbookingapp.service;

import java.util.Map;

import com.example.ticketbookingapp.model.City;

public interface CityService {
    City addCity(City city) throws Exception;

    City findCity(String cityId);

    Map<String, City> getAllCities();
}
