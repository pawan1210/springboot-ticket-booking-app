package com.example.ticketbookingapp.service;

import java.util.Map;

import com.example.ticketbookingapp.model.Booking;

public interface BookingService {
    void addBooking(Booking booking) throws Exception;

    Booking findBooking(String bookingId);

    Map<String, Booking> getAllBookings();
}
