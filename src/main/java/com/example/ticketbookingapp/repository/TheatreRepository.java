package com.example.ticketbookingapp.repository;

import java.util.Map;

import com.example.ticketbookingapp.model.Theatre;

public interface TheatreRepository {
    void addTheatre(Theatre theatre) throws Exception;

    Theatre findTheatre(String theatreId);

    Map<String, Theatre> getAllTheatres();
}
