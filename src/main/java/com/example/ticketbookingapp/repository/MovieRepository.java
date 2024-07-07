package com.example.ticketbookingapp.repository;

import java.util.Map;

import com.example.ticketbookingapp.model.Movie;

public interface MovieRepository {
    void addMovie(Movie movie);
    
    Movie findMovie(String movieId);

    Map<String, Movie> getAllMovies();
}
