package com.example.ticketbookingapp.repository;

import java.util.Map;

import com.example.ticketbookingapp.model.Show;

public interface ShowRepository {
    void addShow(Show show) throws Exception;

    Show findShow(String showId);

    Map<String, Show> getAllShows();
}
