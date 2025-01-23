package com.example.movie_ticket_booking_system.repositories;

import com.example.movie_ticket_booking_system.models.Showtime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ShowtimesRepository extends JpaRepository<Showtime, Integer> {
    List<Showtime> findAllByMovieId(Integer movieId);
    List<Showtime> findAllByTheater(String theater);
}
