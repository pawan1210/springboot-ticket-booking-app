package com.example.ticketbookingapp.controller;

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

import com.example.ticketbookingapp.dto.AddTheatreDto;
import com.example.ticketbookingapp.dto.ApiResponse;
import com.example.ticketbookingapp.exceptions.TheatreAlreadyExistsException;
import com.example.ticketbookingapp.model.Theatre;
import com.example.ticketbookingapp.service.TheatreService;

import jakarta.validation.Valid;

import java.util.Map;

@RestController()
@RequestMapping("/v1/internal/theatre")
public class TheatreController {
    @Autowired
    private TheatreService theatreService;

    @PostMapping("")
    public ResponseEntity<?> addTheatre(@Valid @RequestBody AddTheatreDto addTheatreDto, BindingResult bindingResult) {
        ApiResponse<Theatre> response = new ApiResponse<>();

        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(response.constructResponse(null, bindingResult.getFieldError().getDefaultMessage()));
        }

        try {
            Theatre theatre = addTheatreDto.toTheatre();
            
            this.theatreService.addTheatre(theatre);
            
            return ResponseEntity.status(HttpStatus.CREATED).body(response.constructResponse(theatre, null));
        } catch (TheatreAlreadyExistsException e) {
            return ResponseEntity.badRequest().body(response.constructResponse(null, e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response.constructResponse(null, e.getMessage()));
        }
    }

    @GetMapping("/{theatreId}")
    public ResponseEntity<?> findTheatre(@PathVariable String theatreId) {
        ApiResponse<Theatre> response = new ApiResponse<>();

        if (theatreId == null || theatreId.length() == 0) {
            return ResponseEntity.badRequest().body(response.constructResponse(null, "theatreId is required"));
        }

        Theatre theatre = theatreService.findTheatre(theatreId);

        if (theatre == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response.constructResponse(null, "theatre not found"));
        }

        return ResponseEntity.status(HttpStatus.OK).body(response.constructResponse(theatre, null));
    }

    @GetMapping("/getall")
    public ResponseEntity<?> getAllTheatres() {
        ApiResponse<Map<String, Theatre>> response = new ApiResponse<>();
        Map<String, Theatre> theatres = theatreService.getAllTheatres();

        return ResponseEntity.status(HttpStatus.OK).body(response.constructResponse(theatres, null));
    }
}
