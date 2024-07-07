package com.example.ticketbookingapp.repository;

import java.util.Map;

import com.example.ticketbookingapp.model.Booking;

public interface BookingRepository {
    void addBooking(Booking booking) throws Exception;

    Booking findBooking(String bookingId);

    Map<String, Booking> getAllBookings();
}
