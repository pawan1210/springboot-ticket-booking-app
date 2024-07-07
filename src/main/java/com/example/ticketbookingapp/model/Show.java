package com.example.ticketbookingapp.model;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

public class Show {
    private Map<Integer, Seat> seats;
    private String id;
    private String theatreId;
    private String movieId;
    private int totalSeats;

    public Show() {
        this.seats = new HashMap<>();
    }

    private void iniitalizeSeats() {
        for (int i=0; i<this.totalSeats; i++) {
            this.seats.put(i+1, new Seat(i + 1));
        }
    }

    public Show addSeats(List<Integer> seats) {
        if (seats != null) {
            for (int i=0; i<seats.size(); i++) {
                this.seats.get(seats.get(i)).setBooked(true);
            }
        }
        
        return this;
    }

    public Map<Integer, Seat> getSeats() {
        return this.seats;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTheatreId() {
        return this.theatreId;
    }

    public Show setTheatreId(String theatreId) {
        this.theatreId = theatreId;

        return this;
    }

    public Show setMovieId(String movieId) {
        this.movieId = movieId;

        return this;
    }

    public Show setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
        
        this.iniitalizeSeats();

        return this;
    }

    public String getMovieId() {
        return this.movieId;
    }
}
