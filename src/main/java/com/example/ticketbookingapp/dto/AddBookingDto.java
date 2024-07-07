package com.example.ticketbookingapp.dto;


import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import java.util.List;

import com.example.ticketbookingapp.model.Booking;

@Data
public class AddBookingDto {
    @NotEmpty(message = "showId is required")
    private String showId;

    @NotEmpty(message = "seat numbers are required")
    private List<Integer> seats;

    @NotEmpty(message = "userId is required")
    private String userId;

    public Booking toBooking() {
        return new Booking()
        .setSeats(this.seats)
        .setShowId(this.showId)
        .setUserId(this.userId);
    }
}
