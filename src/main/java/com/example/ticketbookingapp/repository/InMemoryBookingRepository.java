package com.example.ticketbookingapp.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.ticketbookingapp.model.Booking;


@Repository
public class InMemoryBookingRepository implements BookingRepository {
    private Map<String, Booking> bookings;

    public InMemoryBookingRepository() {
        this.bookings = new HashMap<>();
    }
    
    public void addBooking(Booking booking) {
        for (int i=0; i<booking.getSeats().size(); i++) {
            
        }

        this.bookings.put(booking.getId(), booking);
    }

    public Booking findBooking(String bookingId) {
        return this.bookings.get(bookingId);
    }

    public Map<String, Booking> getAllBookings() {
        return this.bookings;
    }

    public List<Booking> getBookingsByUserId(String userId) {
        List<Booking> userBookings = new ArrayList<>();

        for (Map.Entry<String, Booking> entry: this.bookings.entrySet()) {
            Booking booking = (Booking) entry.getValue();

            if (booking.getUserId().equals(userId)) {
                userBookings.add(booking);
            }
        }

        return userBookings;
    }
}
