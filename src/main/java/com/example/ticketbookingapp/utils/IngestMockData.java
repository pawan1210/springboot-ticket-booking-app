package com.example.ticketbookingapp.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.ticketbookingapp.model.City;
import com.example.ticketbookingapp.model.Movie;
import com.example.ticketbookingapp.model.Show;
import com.example.ticketbookingapp.model.Theatre;
import com.example.ticketbookingapp.model.User;
import com.example.ticketbookingapp.service.CityService;
import com.example.ticketbookingapp.service.MovieService;
import com.example.ticketbookingapp.service.ShowService;
import com.example.ticketbookingapp.service.TheatreService;
import com.example.ticketbookingapp.service.UserService;

@Component
public class IngestMockData {
    @Autowired
    UserService userService;
    
    @Autowired
    TheatreService theatreService;

    @Autowired
    CityService cityService;

    @Autowired
    MovieService movieService;

    @Autowired
    ShowService showService;

    public void ingestData() {
        User testUser = new User("testUser");
        City testCity = new City("testCity");
        Theatre testTheatre = new Theatre("testTheatre");
        Movie testMovie = new Movie("testMovie");
        Show testShow = new Show();
        
        try {
            userService.addUser(testUser);

            testCity = cityService.addCity(testCity);

            testTheatre.setCityId(testCity.getId());
            
            theatreService.addTheatre(testTheatre);

            movieService.addMovie(testMovie);

            testShow = testShow.setTheatreId(testTheatre.getId()).setMovieId(testMovie.getId()).setTotalSeats(5);
            
            showService.addShow(testShow);
        } catch (Exception e) {

        }
    }
}
