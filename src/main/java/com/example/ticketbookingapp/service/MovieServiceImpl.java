package com.example.ticketbookingapp.service;

import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ticketbookingapp.model.Movie;
import com.example.ticketbookingapp.repository.MovieRepository;

@Service
public class MovieServiceImpl implements MovieService {
    @Autowired
    private MovieRepository movieRepository;

    public void addMovie(Movie movie) {
        String movieId = UUID.randomUUID().toString();
        
        movie.setId(movieId);

        this.movieRepository.addMovie(movie);
    }

    public Movie findMovie(String movieId) {
       return this.movieRepository.findMovie(movieId);
    }

    public Map<String, Movie> getAllMovies() {
        return this.movieRepository.getAllMovies();
    }
}
