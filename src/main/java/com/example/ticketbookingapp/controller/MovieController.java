package com.example.ticketbookingapp.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ticketbookingapp.dto.AddMovieDto;
import com.example.ticketbookingapp.dto.ApiResponse;
import com.example.ticketbookingapp.exceptions.MovieAlreadyExistsException;
import com.example.ticketbookingapp.model.Movie;
import com.example.ticketbookingapp.service.MovieService;

import jakarta.validation.Valid;

@RestController()
@RequestMapping("/v1/internal/movie")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @PostMapping("")
    public ResponseEntity<?> addMovie(@Valid @RequestBody AddMovieDto addMovieDto, BindingResult bindingResult) {
        ApiResponse<Movie> response = new ApiResponse<>();

        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(response.constructResponse(null, bindingResult.getFieldError().getDefaultMessage()));
        }

        try {
            Movie movie = addMovieDto.toMovie();

            this.movieService.addMovie(movie);
            
            return ResponseEntity.status(HttpStatus.CREATED).body(response.constructResponse(movie, null));
        } catch (MovieAlreadyExistsException e) {
            return ResponseEntity.badRequest().body(response.constructResponse(null, e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response.constructResponse(null, e.getMessage()));
        }
    }

    @GetMapping("/{movieId}")
    public ResponseEntity<?> findMovie(@PathVariable String movieId) {
        ApiResponse<Movie> response = new ApiResponse<>();

        if (movieId == null || movieId.length() == 0) {
            return ResponseEntity.badRequest().body(response.constructResponse(null, "movidId is required"));
        }

        Movie movie = movieService.findMovie(movieId);

        if (movie == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response.constructResponse(null, "movie not found"));
        }

        return ResponseEntity.status(HttpStatus.OK).body(response.constructResponse(movie, null));
    }

    @GetMapping("/getall")
    public ResponseEntity<?> getAllMovies() {
        ApiResponse<Map<String, Movie>> response = new ApiResponse<>();
        Map<String, Movie> movies = movieService.getAllMovies();

        return ResponseEntity.status(HttpStatus.OK).body(response.constructResponse(movies, null));
    }
}
