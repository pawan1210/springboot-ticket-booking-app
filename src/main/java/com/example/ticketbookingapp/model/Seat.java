package com.example.ticketbookingapp.model;

public class Seat {
    private int number;
    private boolean booked;

    public Seat(int number) {
        this.number = number;
    }

    public int getNumber() {
        return this.number;
    }

    public boolean getBooked() {
        return this.booked;
    }

    public Seat setBooked(boolean booked) {
        this.booked = booked;

        return this;
    }
}
