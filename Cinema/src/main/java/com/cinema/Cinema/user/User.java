package com.cinema.Cinema.user;

import com.cinema.Cinema.ticket.Ticket;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Getter
@Setter
@Data
@NoArgsConstructor
public class User {

    private String name;
    private String surname;
    private String pass;
    private String email;
    private long id = -1;
    private AtomicLong lastTicketAdded;
    // private Boolean admin; -> When we are allowed to use databases, add

    private Map<Long,Ticket> tickets;
    public User(String name, String surname, String pass, String email) {
        this.name = name;
        this.surname = surname;
        this.pass = pass;
        this.email = email;
        this.lastTicketAdded = new AtomicLong();
        this.tickets = new ConcurrentHashMap<>();
    }

    public Collection<Ticket> allTickets(){
        return this.tickets.values();
    }




}
