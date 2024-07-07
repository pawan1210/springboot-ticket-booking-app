package com.example.ticketbookingapp.service;

import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.ticketbookingapp.model.City;
import com.example.ticketbookingapp.model.Theatre;
import com.example.ticketbookingapp.repository.TheatreRepository;

@Service
public class TheatreServiceImpl implements TheatreService {
    @Autowired
    private TheatreRepository theatreRepository;

    @Autowired 
    CityService cityService;

    public TheatreServiceImpl(@Qualifier("inMemoryTheatreRepository") TheatreRepository theatreRepository) {
        this.theatreRepository = theatreRepository;
    }

    @Override
    public void addTheatre(Theatre theatre) throws Exception{
        City existingCity = this.cityService.findCity(theatre.getCityId());

        if (existingCity == null) {
            throw new Exception("city not found");
        }

        String theatreId = UUID.randomUUID().toString();

        theatre.setId(theatreId);

        this.theatreRepository.addTheatre(theatre);
    }

    @Override
    public Theatre findTheatre(String theatreId) {
        return this.theatreRepository.findTheatre(theatreId);
    }

    @Override
    public Map<String, Theatre> getAllTheatres() {
        return this.theatreRepository.getAllTheatres();
    }
}
