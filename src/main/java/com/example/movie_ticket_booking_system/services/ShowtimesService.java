package com.example.movie_ticket_booking_system.services;

import com.example.movie_ticket_booking_system.exceptions.ShowtimeNotFoundException;
import com.example.movie_ticket_booking_system.exceptions.ShowtimesOverlapException;
import com.example.movie_ticket_booking_system.models.Showtime;
import com.example.movie_ticket_booking_system.repositories.ShowtimesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.nonNull;

@Service
public class ShowtimesService {

    @Autowired
    private ShowtimesRepository showtimesRepository;

    public void addShowtime(Showtime showtime) {
        this.getShowtimesByTheater(showtime.getTheater()).stream()
                .filter(existsShowtime -> this.doTimesOverlap(existsShowtime, showtime))
                .findAny().ifPresent(existsShowtime -> {
                    throw new ShowtimesOverlapException(existsShowtime);
                });
        this.showtimesRepository.save(showtime);
    }

    public void updateShowtime(Integer showTimeId, Showtime showtimeUpdate) {
        Showtime showtime = this.getShowtimeById(showTimeId);
        showtime.setTheater(nonNull(showtimeUpdate.getTheater()) ? showtimeUpdate.getTheater() : showtime.getTheater());
        showtime.setMovieId(nonNull(showtimeUpdate.getMovieId()) ? showtimeUpdate.getMovieId() : showtime.getMovieId());
        showtime.setStartTime(nonNull(showtimeUpdate.getStartTime()) ? showtimeUpdate.getStartTime() : showtime.getStartTime());
        showtime.setEndTime(nonNull(showtimeUpdate.getEndTime()) ? showtimeUpdate.getEndTime() : showtime.getEndTime());
        this.addShowtime(showtime);
    }

    public void deleteShowtime(Integer showtimeId) {
        this.getShowtimeById(showtimeId);
        this.showtimesRepository.deleteById(showtimeId);
    }

    public Showtime getShowtimeById(Integer showtimeId) {
        return this.showtimesRepository.findById(showtimeId)
                .orElseThrow(() -> new ShowtimeNotFoundException(showtimeId));
    }

    public List<Showtime> getShowtimesByMovie(Integer movieId) {
        return this.showtimesRepository.findAllByMovieId(movieId);
    }

    public List<Showtime> getShowtimesByTheater(String theater) {
        return this.showtimesRepository.findAllByTheater(theater);
    }

    private boolean doTimesOverlap(Showtime showtime1, Showtime showtime2) {
        return showtime1.getStartTime().isBefore(showtime2.getEndTime()) &&
                showtime1.getEndTime().isAfter(showtime2.getStartTime());
    }
}
