package com.example.ticketbookingapp.repository;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Repository;

import com.example.ticketbookingapp.model.City;


@Repository
public class InMemoryCityRepository implements CityRepository{
    private Map<String, City> cities;
    private Set<String> names;

    public InMemoryCityRepository() {
        this.cities = new HashMap<>();
        this.names = new HashSet<>();
    }

    public void addCity(City city) throws Exception {
        String cityId = city.getId();

        if (this.cities.containsKey(cityId) || this.names.contains(city.getName())) {
            throw new Exception("city already exists");
        }

        this.cities.put(cityId, city);
        this.names.add(city.getName());
    }

    public City findCity(String cityId) {
        return this.cities.get(cityId);
    }

    public Map<String, City> getAllCities() {
        return this.cities;
    }
}
