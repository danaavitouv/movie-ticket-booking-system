package com.example.movie_ticket_booking_system.exceptions;

public class ShowtimeFullyBookedException extends RuntimeException {
    public ShowtimeFullyBookedException(Integer showtimeId) {
        super(String.format("Showtime with id %d is fully booked", showtimeId));
    }
}
