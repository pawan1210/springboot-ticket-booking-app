package com.example.ticketbookingapp.service;

import java.util.Map;
import java.util.List;

import com.example.ticketbookingapp.model.Booking;

public interface BookingService {
    Booking addBooking(Booking booking) throws Exception;

    Booking findBooking(String bookingId);

    Map<String, Booking> getAllBookings();

    List<Booking> getBookingsByUserId(String userId) throws Exception;
}
