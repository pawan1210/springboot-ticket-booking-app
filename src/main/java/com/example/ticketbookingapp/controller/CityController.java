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

import com.example.ticketbookingapp.dto.AddCityDto;
import com.example.ticketbookingapp.dto.ApiResponse;
import com.example.ticketbookingapp.exceptions.CityAlreadyExistsException;
import com.example.ticketbookingapp.model.City;
import com.example.ticketbookingapp.service.CityService;

import jakarta.validation.Valid;

@RestController()
@RequestMapping("/v1/internal/city")
public class CityController {
    @Autowired
    private CityService cityService;

    @PostMapping("")
    public ResponseEntity<?> addCity(@Valid @RequestBody AddCityDto addCityDto, BindingResult bindingResult) {
        ApiResponse<City> response = new ApiResponse<>();

        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(response.constructResponse(null, bindingResult.getFieldError().getDefaultMessage()));
        }

        try {
            City city = addCityDto.toCity();

            this.cityService.addCity(city);
            
            return ResponseEntity.status(HttpStatus.CREATED).body(response.constructResponse(city, null));
        } catch (CityAlreadyExistsException e) {
            return ResponseEntity.badRequest().body(response.constructResponse(null, e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response.constructResponse(null, e.getMessage()));
        }
    }

    @GetMapping("/{cityId}")
    public ResponseEntity<?> findCity(@PathVariable String cityId) {
        ApiResponse<City> response = new ApiResponse<>();

        if (cityId == null || cityId.length() == 0) {
            return ResponseEntity.badRequest().body(response.constructResponse(null, "cityId is required"));
        }

        City city = cityService.findCity(cityId);

        if (city == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response.constructResponse(null, "city not found"));
        }

        return ResponseEntity.status(HttpStatus.OK).body(response.constructResponse(city, null));
    }

    @GetMapping("/getall")
    public ResponseEntity<?> getAllCities() {
        ApiResponse<Map<String, City>> response = new ApiResponse<>();
        Map<String, City> cities = cityService.getAllCities();

        return ResponseEntity.status(HttpStatus.OK).body(response.constructResponse(cities, null));
    }
}
