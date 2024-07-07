package com.example.ticketbookingapp.service;

import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ticketbookingapp.model.City;
import com.example.ticketbookingapp.repository.CityRepository;

@Service
public class CityServiceImpl implements CityService {
    @Autowired
    CityRepository cityRepository;

    public City addCity(City city) throws Exception {
        String cityId = UUID.randomUUID().toString();

        city.setId(cityId);

        cityRepository.addCity(city);

        return city;
    }

    public City findCity(String cityId) {
        return this.cityRepository.findCity(cityId);
    }

    public Map<String, City> getAllCities() {
        return this.cityRepository.getAllCities();
    }
}
