package com.example.movie_ticket_booking_system.exceptions;

import com.example.movie_ticket_booking_system.models.Showtime;

public class ShowtimesOverlapException extends RuntimeException {
    public ShowtimesOverlapException(Showtime existsShowtime) {
        super(String.format("Showtime is overlapping with existing showtime with id %s", existsShowtime.getId()));
    }
}
