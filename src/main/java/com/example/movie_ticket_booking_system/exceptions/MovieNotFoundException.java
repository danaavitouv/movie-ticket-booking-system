package com.example.movie_ticket_booking_system.exceptions;

public class MovieNotFoundException extends RuntimeException {
    public MovieNotFoundException(String movieTitle) {
        super(String.format("Movie with title %s not found", movieTitle));
    }
}
