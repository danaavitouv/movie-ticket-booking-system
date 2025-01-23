package com.example.movie_ticket_booking_system.exceptions;

import com.example.movie_ticket_booking_system.models.Booking;

public class SeatAlreadyTakenException extends RuntimeException {
    public SeatAlreadyTakenException(Booking booking) {
        super(String.format("Seat number %d in showtime with id %d is already taken",
                booking.getSeatNumber(), booking.getShowtimeId()));
    }
}
