package com.example.ticketbookingapp.service;

import java.util.Map;

import com.example.ticketbookingapp.model.Show;

public interface ShowService {
    void addShow(Show show) throws Exception;

    Show findShow(String showId);

    Map<String, Show> getAllShows();
}
