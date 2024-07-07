package com.example.ticketbookingapp.dto;


import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import com.example.ticketbookingapp.model.Theatre;

@Data
public class AddTheatreDto {
    @NotEmpty(message = "theatre name is required")
    private String name;

    @NotEmpty(message = "cityId is required")
    private String cityId;

    public Theatre toTheatre() {
        return new Theatre().setName(this.name).setCityId(this.cityId);
    }
}
