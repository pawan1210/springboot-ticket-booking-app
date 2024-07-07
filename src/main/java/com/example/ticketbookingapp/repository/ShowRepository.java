package com.example.ticketbookingapp.repository;

import java.util.List;

import com.example.ticketbookingapp.model.Show;

public interface ShowRepository {
    void addShow(Show show) throws Exception;

    Show findShow(String showId);

    List<Show> getShows(String cityId, String theatreId);

    void fillSeat(String showId, List<Integer> seats) throws Exception;
}
