package com.example.ticketbookingapp.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.ticketbookingapp.model.Show;

@Repository
public class InMemoryShowRepository implements ShowRepository {
    private Map<String, Show> shows;

    public InMemoryShowRepository() {
        this.shows = new HashMap<>();
    }
    
    public void addShow(Show show) throws Exception {
        if (this.shows.containsKey(show.getId())) {
            throw new Exception("show already exists");
        }

        this.shows.put(show.getId(), show);
    }

    public Show findShow(String showId) {
        return this.shows.get(showId);
    }

    public Map<String, Show> getAllShows() {
        return this.shows;
    }

    public void fillSeat(String showId, List<Integer> seats) throws Exception {
        if (!this.shows.containsKey(showId)) {
            throw new Exception("show doesn't exists");
        }

        this.shows.get(showId).addSeats(seats);
    }
}
