package com.example.ticketbookingapp.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.example.ticketbookingapp.model.Movie;

@Repository
public class InMemoryMovieRepository implements MovieRepository {
    private Map<String, Movie> movies;

    public InMemoryMovieRepository() {
        this.movies = new HashMap<>();
    }

    public void addMovie(Movie movie) {
        this.movies.put(movie.getId(), movie);
    }


    public Movie findMovie(String movieId) {
        return this.movies.get(movieId);
    }

    public Map<String, Movie> getAllMovies() {
        return this.movies;
    }
}
