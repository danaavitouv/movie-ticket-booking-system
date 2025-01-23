package com.example.movie_ticket_booking_system.repositories;

import com.example.movie_ticket_booking_system.models.SystemUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UsersRepository extends JpaRepository<SystemUser, Integer> {
    Optional<SystemUser> findByName(String name);
}
