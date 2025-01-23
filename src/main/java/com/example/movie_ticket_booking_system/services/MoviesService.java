package com.example.movie_ticket_booking_system.services;

import com.example.movie_ticket_booking_system.exceptions.MovieNotFoundException;
import com.example.movie_ticket_booking_system.models.Movie;
import com.example.movie_ticket_booking_system.repositories.MoviesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.nonNull;

@Service
public class MoviesService {

    @Autowired
    private MoviesRepository moviesRepository;

    public void addMovie(Movie movie) {
        this.moviesRepository.save(movie);
    }

    public void updateMovie(String movieTitle, Movie movieUpdate) {
        Movie movie = this.getMovieByTitle(movieTitle);
        movie.setTitle(nonNull(movieUpdate.getTitle()) ? movieUpdate.getTitle() : movie.getTitle());
        movie.setGenre(nonNull(movieUpdate.getGenre()) ? movieUpdate.getGenre() : movie.getGenre());
        movie.setDuration(nonNull(movieUpdate.getDuration()) ? movieUpdate.getDuration() : movie.getDuration());
        movie.setRating(nonNull(movieUpdate.getRating()) ? movieUpdate.getRating() : movie.getRating());
        movie.setReleaseYear(nonNull(movieUpdate.getReleaseYear()) ? movieUpdate.getReleaseYear() : movie.getReleaseYear());
        this.moviesRepository.save(movie);
    }

    public void deleteMovie(String movieTitle) {
        this.getMovieByTitle(movieTitle);
        this.moviesRepository.deleteByTitle(movieTitle);
    }

    public Movie getMovieByTitle(String movieTitle) {
        return this.moviesRepository.findByTitle(movieTitle)
                .orElseThrow(() -> new MovieNotFoundException(movieTitle));
    }

    public List<Movie> getAllMovies() {
        return this.moviesRepository.findAll();
    }
}
