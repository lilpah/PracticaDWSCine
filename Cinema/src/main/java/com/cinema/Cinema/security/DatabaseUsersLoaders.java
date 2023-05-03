package com.cinema.Cinema.security;

import com.cinema.Cinema.entities.User;
import com.cinema.Cinema.repositories.UserRepository;
import com.cinema.Cinema.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DatabaseUsersLoaders {

    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    /*@PostConstruct
    private void initDatabase() {
        userService.addUser(new User("normalUser", "normalSurname",  passwordEncoder.encode("user"), "normalUser@lacartelera.com", "USER"));
        userService.addUser(new User("admin", "adminSurname",  passwordEncoder.encode("admin"), "admin@lacartelera.com", "ADMIN", "USER"));
    }

     */


}
