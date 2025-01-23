package com.example.movie_ticket_booking_system.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String genre;
    private Integer duration;
    private Double rating;
    @Column(name = "release_year")
    private Integer releaseYear;
}
