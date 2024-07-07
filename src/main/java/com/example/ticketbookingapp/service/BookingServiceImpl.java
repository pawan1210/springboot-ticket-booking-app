package com.example.ticketbookingapp.service;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Semaphore;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ticketbookingapp.model.Booking;
import com.example.ticketbookingapp.model.Movie;
import com.example.ticketbookingapp.model.Seat;
import com.example.ticketbookingapp.model.Show;
import com.example.ticketbookingapp.model.Theatre;
import com.example.ticketbookingapp.model.User;
import com.example.ticketbookingapp.repository.BookingRepository;


@Service
public class BookingServiceImpl implements BookingService {
    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private TheatreService theatreService;

    @Autowired
    private MovieService movieService;

    @Autowired
    private ShowService showService;

    private Semaphore semaphore;

    public BookingServiceImpl() {
        this.semaphore = new Semaphore(1);
    }

    public Booking addBooking(Booking booking) throws Exception {
        try {
            this.semaphore.acquire();

            Show existingShow = showService.findShow(booking.getShowId());

            if (existingShow == null) {
                throw new Exception("show doesn't exists");
            }

            Theatre existingTheatre = theatreService.findTheatre(existingShow.getTheatreId());

            if (existingTheatre == null) {
                throw new Exception("theatre doesn't exists");
            }

            Movie existingMovie = movieService.findMovie(existingShow.getMovieId());

            if (existingMovie == null) {
                throw new Exception("movie doesn't exists");
            }

            for (int i=0; i<booking.getSeats().size(); i++) {
                int seatNumber = booking.getSeats().get(i);
                Seat existingSeat = existingShow.getSeats().get(seatNumber);

                if (existingSeat == null) {
                    throw new Exception("seat doesn't exists");
                }

                if (existingSeat.getBooked()) {
                    throw new Exception("seat already booked");
                }
            }

            String bookingId = UUID.randomUUID().toString();

            booking.setId(bookingId).setMovieId(existingShow.getMovieId()).setTheatreId(existingShow.getId());

            this.bookingRepository.addBooking(booking);

            this.showService.fillSeat(existingShow.getId(), booking.getSeats());

            return booking;
        } catch (Exception e) {
            throw e;
        } finally {
            this.semaphore.release();
        }
    }

    public Booking findBooking(String bookingId) {
        return this.bookingRepository.findBooking(bookingId);
    }

    public Map<String, Booking> getAllBookings() {
        return this.bookingRepository.getAllBookings();
    }

    public List<Booking> getBookingsByUserId(String userId) throws Exception{
        User existingUser = userService.findUser(userId);

        if (existingUser == null) {
            throw new Exception("user doesn't exists");
        }

        return this.bookingRepository.getBookingsByUserId(userId);
    }
}
