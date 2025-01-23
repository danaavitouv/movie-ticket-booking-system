package com.example.movie_ticket_booking_system.controller;

import com.example.movie_ticket_booking_system.models.SystemUser;
import com.example.movie_ticket_booking_system.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UsersController {

    @Autowired
    private UsersService usersService;

    @GetMapping("/register")
    public String showRegistrationForm() {
        return "register";
    }

    @PostMapping(path = "/register")
    public String registerUser(@RequestParam("username") String username,
                               @RequestParam("password") String password,
                               @RequestParam("email") String email) {
        this.usersService.registerUser(new SystemUser(username, password, email));
        return "redirect:/login";
    }
}
