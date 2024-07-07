package com.example.ticketbookingapp.service;

import java.util.List;

import com.example.ticketbookingapp.model.Show;

public interface ShowService {
    void addShow(Show show) throws Exception;

    Show findShow(String showId);

    List<Show> getShows(String cityName, String theatreName);

    void fillSeat(String showId, List<Integer> seats) throws Exception;
}
