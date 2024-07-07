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

import com.example.ticketbookingapp.dto.AddBookingDto;
import com.example.ticketbookingapp.dto.ApiResponse;
import com.example.ticketbookingapp.model.Booking;
import com.example.ticketbookingapp.service.BookingService;

import jakarta.validation.Valid;

@RestController()
@RequestMapping("/v1/booking")
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @PostMapping("")
    public ResponseEntity<?> addBooking(@Valid @RequestBody AddBookingDto addBookingDto, BindingResult bindingResult) {
        ApiResponse<Booking> response = new ApiResponse<>();

        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(response.constructResponse(null, bindingResult.getFieldError().getDefaultMessage()));
        }

        try {
            Booking booking = addBookingDto.toBooking();

            this.bookingService.addBooking(booking);
            
            return ResponseEntity.status(HttpStatus.CREATED).body(response.constructResponse(booking, null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response.constructResponse(null, e.getMessage()));
        }
    }

    @GetMapping("/{bookingId}")
    public ResponseEntity<?> findBooking(@PathVariable String bookingId) {
        ApiResponse<Booking> response = new ApiResponse<>();

        if (bookingId == null || bookingId.length() == 0) {
            return ResponseEntity.badRequest().body(response.constructResponse(null, "bookingId is required"));
        }

        Booking booking = bookingService.findBooking(bookingId);

        if (booking == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response.constructResponse(null, "booking not found"));
        }

        return ResponseEntity.status(HttpStatus.OK).body(response.constructResponse(booking, null));
    }

    @GetMapping("/getall")
    public ResponseEntity<?> getAllCities() {
        ApiResponse<Map<String, Booking>> response = new ApiResponse<>();
        Map<String, Booking> cities = bookingService.getAllBookings();

        return ResponseEntity.status(HttpStatus.OK).body(response.constructResponse(cities, null));
    }
}
