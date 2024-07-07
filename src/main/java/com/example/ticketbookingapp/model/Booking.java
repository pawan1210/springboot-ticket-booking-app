package com.example.ticketbookingapp.model;

import java.util.List;

public class Booking {
    private String movieId;
    private String theatreId;
    private String showId;
    private String userId;
    private String id;
    private List<Integer> seats;

    public Booking() {

    }

    public String getId() {
        return this.id;
    }

    public Booking setId(String id) {
        this.id = id;

        return this;
    }

    public Booking setUserId(String userId) {
        this.userId = userId;

        return this;
    }

    public String getUserId() {
        return this.userId;
    }

    public String getMovieId() {
        return this.movieId;
    }

    public Booking setMovieId(String movieId) {
        this.movieId = movieId;

        return this;
    }

    public String getTheatreId() {
        return this.theatreId;
    }

    public Booking setTheatreId(String theatreId) {
        this.theatreId = theatreId;

        return this;
    }

    public String getShowId() {
        return this.showId;
    }

    public Booking setShowId(String showId) {
        this.showId = showId;

        return this;
    }

    public List<Integer> getSeats() {
        return this.seats;
    }

    public Booking setSeats(List<Integer>seats) {
        this.seats = seats;

        return this;
    }
}
