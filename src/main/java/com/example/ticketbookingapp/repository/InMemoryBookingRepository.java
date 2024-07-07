package com.example.ticketbookingapp.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.example.ticketbookingapp.model.Booking;


@Repository
public class InMemoryBookingRepository implements BookingRepository {
    private Map<String, Booking> bookings;

    public InMemoryBookingRepository() {
        this.bookings = new HashMap<>();
    }
    
    public void addBooking(Booking booking) throws Exception {
        if (this.bookings.containsKey(booking.getId())) {
            throw new Exception("booking already exists");
        }

        this.bookings.put(booking.getId(), booking);
    }

    public Booking findBooking(String bookingId) {
        return this.bookings.get(bookingId);
    }

    public Map<String, Booking> getAllBookings() {
        return this.bookings;
    }
}
