package com.example.ticketbookingapp.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.example.ticketbookingapp.exceptions.TheatreAlreadyExistsException;
import com.example.ticketbookingapp.model.Theatre;

@Repository
public class InMemoryTheatreRepository implements TheatreRepository{
    private Map<String, Theatre> theatres;
    
    public InMemoryTheatreRepository() {
        this.theatres = new HashMap<>();
    }

    public void addTheatre(Theatre theatre) throws Exception {
        String theatreId = theatre.getId();

        if (this.theatres.containsKey(theatreId)) {
            throw new TheatreAlreadyExistsException("theatre already exists");
        }

        this.theatres.put(theatreId, theatre);
    }

    public Theatre findTheatre(String theatreId){
        return this.theatres.get(theatreId);
    }

    public Map<String, Theatre> getAllTheatres() {
        return this.theatres;
    }
}
