package com.example.movie_ticket_booking_system.services;

import com.example.movie_ticket_booking_system.models.Role;
import com.example.movie_ticket_booking_system.models.SystemUser;
import com.example.movie_ticket_booking_system.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UsersService implements UserDetailsService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SystemUser user = this.getUserByName(username);
        return new User(
                user.getName(),
                user.getPassword(),
                true, true, true, true,
                Collections.singleton(new SimpleGrantedAuthority(user.getRole().name()))
        );
    }

    public void registerUser(SystemUser user) {
        user.setPassword(this.passwordEncoder().encode(user.getPassword()));
        user.setRole(Role.Customer);
        this.usersRepository.save(user);
    }

    public SystemUser getUserByName(String username) {
        return this.usersRepository.findByName(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User %s not found", username)));
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
