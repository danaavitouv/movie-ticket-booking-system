package com.example.movie_ticket_booking_system.controller;

import com.example.movie_ticket_booking_system.models.Movie;
import com.example.movie_ticket_booking_system.services.MoviesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MoviesController {

    @Autowired
    private MoviesService moviesService;

    @PostMapping
    public void addMovie(@RequestBody Movie movie) {
        this.moviesService.addMovie(movie);
    }

    @GetMapping(path = "/{movieTitle}")
    public Movie getMovieByTitle(@PathVariable String movieTitle) {
        return this.moviesService.getMovieByTitle(movieTitle);
    }

    @DeleteMapping(path = "/{movieTitle}")
    public void deleteMovie(@PathVariable String movieTitle) {
        this.moviesService.deleteMovie(movieTitle);
    }

    @GetMapping(path = "/all")
    public List<Movie> getAllMovies() {
        return this.moviesService.getAllMovies();
    }

    @PostMapping(path = "/update/{movieTitle}")
    public void updateMovie(@PathVariable String movieTitle, @RequestBody Movie movie) {
        this.moviesService.updateMovie(movieTitle, movie);
    }
}
