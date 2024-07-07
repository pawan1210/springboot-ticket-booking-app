package com.example.ticketbookingapp.dto;


import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import com.example.ticketbookingapp.model.City;

@Data
public class AddCityDto {
    @NotEmpty(message = "city name is required")
    private String name;

    public City toCity() {
        return new City().setName(this.name);
    }
}
