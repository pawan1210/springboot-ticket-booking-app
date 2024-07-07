package com.example.ticketbookingapp.service;

import java.util.Map;

import com.example.ticketbookingapp.model.Theatre;

public interface TheatreService {
    void addTheatre(Theatre theatre) throws Exception;

    Theatre findTheatre(String theatreId);

    Map<String, Theatre> getAllTheatres();
}
