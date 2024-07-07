package com.example.ticketbookingapp.service;

import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ticketbookingapp.model.Movie;
import com.example.ticketbookingapp.model.Show;
import com.example.ticketbookingapp.model.Theatre;
import com.example.ticketbookingapp.repository.ShowRepository;


@Service
public class ShowServiceImpl implements ShowService {
    @Autowired
    MovieService movieService;

    @Autowired
    TheatreService theatreService;

    @Autowired
    ShowRepository showRepository;

    public void addShow(Show show) throws Exception{
        Theatre existingTheatre = theatreService.findTheatre(show.getTheatreId());

        if (existingTheatre == null) {
            throw new Exception("theatre doesn't exists");
        }

        Movie existingMovie = movieService.findMovie(show.getMovieId());

        if (existingMovie == null) {
            throw new Exception("movie doesn't exists");
        }

        String showId = UUID.randomUUID().toString();

        show.setId(showId);

        this.showRepository.addShow(show);
    }

    public Show findShow(String showId) {
        return this.showRepository.findShow(showId);
    }

    public Map<String, Show> getAllShows() {
        return this.showRepository.getAllShows();
    }
}
