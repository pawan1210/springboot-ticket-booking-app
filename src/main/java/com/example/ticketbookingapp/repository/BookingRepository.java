package com.example.ticketbookingapp.repository;

import java.util.Map;
import java.util.List;

import com.example.ticketbookingapp.model.Booking;

public interface BookingRepository {
    void addBooking(Booking booking);

    Booking findBooking(String bookingId);

    Map<String, Booking> getAllBookings();

    List<Booking> getBookingsByUserId(String userId);
}
