package com.example.ticketbookingapp.dto;


import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import com.example.ticketbookingapp.model.Movie;

@Data
public class AddMovieDto {
    @NotEmpty(message = "movie name is required")
    private String name;

    public Movie toMovie() {
        return new Movie().setName(this.name);
    }
}
