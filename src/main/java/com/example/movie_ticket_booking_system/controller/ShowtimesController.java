package com.example.movie_ticket_booking_system.controller;

import com.example.movie_ticket_booking_system.models.Showtime;
import com.example.movie_ticket_booking_system.services.ShowtimesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/showtimes")
public class ShowtimesController {

    @Autowired
    private ShowtimesService showtimesService;

    @PostMapping
    public void addShowtime(@RequestBody Showtime showtime) {
        this.showtimesService.addShowtime(showtime);
    }

    @GetMapping(path = "/{showtimeId}")
    public Showtime getShowtimeById(@PathVariable Integer showtimeId) {
        return this.showtimesService.getShowtimeById(showtimeId);
    }

    @DeleteMapping(path = "/{showtimeId}")
    public void deleteShowtime(@PathVariable Integer showtimeId) {
        this.showtimesService.deleteShowtime(showtimeId);
    }

    @GetMapping(path = "/byMovie/{movieId}")
    public List<Showtime> getShowtimesByMovie(@PathVariable Integer movieId) {
        return this.showtimesService.getShowtimesByMovie(movieId);
    }

    @GetMapping(path = "/byTheater/{theater}")
    public List<Showtime> getShowtimesByTheater(@PathVariable String theater) {
        return this.showtimesService.getShowtimesByTheater(theater);
    }

    @PostMapping(path = "/update/{showtimeId}")
    public void updateShowtime(@PathVariable Integer showtimeId, @RequestBody Showtime showtime) {
        this.showtimesService.updateShowtime(showtimeId, showtime);
    }
}
