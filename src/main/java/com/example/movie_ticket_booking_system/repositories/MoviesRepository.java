package com.example.movie_ticket_booking_system.repositories;

import com.example.movie_ticket_booking_system.models.Movie;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface MoviesRepository extends JpaRepository<Movie, Integer> {
    Optional<Movie> findByTitle(String title);

    @Transactional
    void deleteByTitle(String title);
}