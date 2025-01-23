package com.example.movie_ticket_booking_system.exceptions;

public class ShowtimeNotFoundException extends RuntimeException {
    public ShowtimeNotFoundException(Integer showtimeId) {
        super(String.format("Show time with id %d not found", showtimeId));
    }
}
