package com.cinema.Cinema.user;

import com.cinema.Cinema.ticket.Ticket;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.random.RandomGenerator;

@Getter
@Setter
@Data
@NoArgsConstructor
public class User {

    private String name;
    private String surname;
    private String pass;
    private String email;
    // private Boolean admin; -> When we are allowed to use databases, add


    public User(String name, String surname, String pass, String email) {
        this.name = name;
        this.surname = surname;
        this.pass = pass;
        this.email = email;
    }



}
