package com.example.movie_ticket_booking_system.services;

import com.example.movie_ticket_booking_system.exceptions.SeatAlreadyTakenException;
import com.example.movie_ticket_booking_system.exceptions.ShowtimeFullyBookedException;
import com.example.movie_ticket_booking_system.models.Booking;
import com.example.movie_ticket_booking_system.repositories.BookingsRepository;
import com.example.movie_ticket_booking_system.request.BookingRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class BookingsService {

    @Autowired
    private BookingsRepository bookingsRepository;

    @Autowired
    private UsersService usersService;

    @Autowired
    private ShowtimesService showtimesService;

    @Value("${movie-price}")
    private Double moviePrice;

    @Value("${max-showtime-seats}")
    private Integer maxShowtimeSeats;

    public void bookATicket(BookingRequest request) {
        this.showtimesService.getShowtimeById(request.getShowtimeId());
        this.checkIfSeatIsAlreadyTaken(request);
        this.checkIfShowtimeIsFullyBooked(request);
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Integer userId = this.usersService.getUserByName(username).getId();
        this.bookingsRepository.save(Booking.builder()
                .userId(userId)
                .showtimeId(request.getShowtimeId())
                .seatNumber(request.getSeatNumber())
                .price(this.moviePrice)
                .build());
    }

    private void checkIfShowtimeIsFullyBooked(BookingRequest request) {
        if (this.bookingsRepository.findByShowtimeId(request.getShowtimeId()).size() == this.maxShowtimeSeats) {
            throw new ShowtimeFullyBookedException(request.getShowtimeId());
        }
    }

    private void checkIfSeatIsAlreadyTaken(BookingRequest request) {
        this.bookingsRepository.findByShowtimeIdAndSeatNumber(request.getShowtimeId(), request.getSeatNumber())
                .ifPresent(booking -> {
                    throw new SeatAlreadyTakenException(booking);
                });
    }
}
