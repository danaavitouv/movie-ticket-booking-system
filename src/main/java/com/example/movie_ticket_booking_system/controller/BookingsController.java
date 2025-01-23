package com.example.movie_ticket_booking_system.controller;

import com.example.movie_ticket_booking_system.request.BookingRequest;
import com.example.movie_ticket_booking_system.services.BookingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bookings")
public class BookingsController {

    @Autowired
    private BookingsService bookingsService;

    @PostMapping
    public void bookATicket(@RequestBody BookingRequest bookingRequest) {
        this.bookingsService.bookATicket(bookingRequest);
    }
}
