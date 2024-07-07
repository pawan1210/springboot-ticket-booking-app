package com.example.ticketbookingapp.service;

import java.util.Map;

import com.example.ticketbookingapp.model.Movie;

public interface MovieService {
    void addMovie(Movie movie) throws Exception;

    Movie findMovie(String movieId);

    Map<String, Movie> getAllMovies();
}
