package com.example.movie_ticket_booking_system.repositories;

import com.example.movie_ticket_booking_system.models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface BookingsRepository extends JpaRepository<Booking, Integer> {
    Optional<Booking> findByShowtimeIdAndSeatNumber(Integer showtimeId, Integer seatNumber);

    List<Booking> findByShowtimeId(Integer showtimeId);
}
