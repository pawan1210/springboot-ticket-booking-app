package com.example.ticketbookingapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.ticketbookingapp.utils.IngestMockData;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class TicketBookingAppApplication {
	@Autowired
	private IngestMockData ingestMockData;

	public static void main(String[] args) {
		SpringApplication.run(TicketBookingAppApplication.class, args);
		
	}

	@PostConstruct
	public void init() {
		ingestMockData.ingestData();
	}
}
