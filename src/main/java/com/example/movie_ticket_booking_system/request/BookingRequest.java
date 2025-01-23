package com.example.movie_ticket_booking_system.request;

import lombok.Data;

@Data
public class BookingRequest {
    private Integer showtimeId;
    private Integer seatNumber;
}
