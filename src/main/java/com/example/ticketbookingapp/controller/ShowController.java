package com.example.ticketbookingapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ticketbookingapp.dto.AddShowDto;
import com.example.ticketbookingapp.dto.ApiResponse;
import com.example.ticketbookingapp.model.Show;
import com.example.ticketbookingapp.service.ShowService;

import jakarta.validation.Valid;

@RestController()
@RequestMapping("/v1/internal/show")
public class ShowController {
    @Autowired
    private ShowService showService;

    @PostMapping("")
    public ResponseEntity<?> addShow(@Valid @RequestBody AddShowDto addShowDto, BindingResult bindingResult) {
        ApiResponse<Show> response = new ApiResponse<>();

        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(response.constructResponse(null, bindingResult.getFieldError().getDefaultMessage()));
        }

        try {
            Show show = addShowDto.toShow();

            this.showService.addShow(show);
            
            return ResponseEntity.status(HttpStatus.CREATED).body(response.constructResponse(show, null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response.constructResponse(null, e.getMessage()));
        }
    }

    @GetMapping("/{showId}")
    public ResponseEntity<?> findShow(@PathVariable String showId) {
        ApiResponse<Show> response = new ApiResponse<>();

        if (showId == null || showId.length() == 0) {
            return ResponseEntity.badRequest().body(response.constructResponse(null, "showId is required"));
        }

        Show show = showService.findShow(showId);

        if (show == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response.constructResponse(null, "show not found"));
        }

        return ResponseEntity.status(HttpStatus.OK).body(response.constructResponse(show, null));
    }

    @GetMapping("/search")
    public ResponseEntity<?> findShows(
        @RequestParam(required = false, defaultValue = "") String cityName, 
        @RequestParam(required = false, defaultValue = "") String theatreName
    ) {
        ApiResponse<List<Show>> response = new ApiResponse<>();
        List<Show> shows = showService.getShows(cityName, theatreName);

        return ResponseEntity.status(HttpStatus.OK).body(response.constructResponse(shows, null));
    }
}
