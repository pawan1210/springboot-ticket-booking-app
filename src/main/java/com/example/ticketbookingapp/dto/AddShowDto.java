package com.example.ticketbookingapp.dto;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import com.example.ticketbookingapp.model.Show;

@Data
public class AddShowDto {
    @NotEmpty(message = "theatreId is required")
    private String theatreId;

    @NotEmpty(message = "movidId is required")
    private String movieId;

    @NotNull(message = "totalSeats is required")
    @Min(value = 1, message = "seats must be greater than zero")
    private int totalSeats;

    public Show toShow() {
        return new Show()
        .setMovieId(this.movieId)
        .setTheatreId(this.theatreId)
        .setTotalSeats(this.totalSeats);
    }
}
