package com.cinema.Cinema.movie;

import com.cinema.Cinema.ticket.Ticket;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Getter
@Setter
@Data
@NoArgsConstructor
public class Movie {
    private String name;
    private String genre;
    private int numSeats = 100;
    private long idMovie = -1;


    public Movie(String name, String genre) {
        this.name = name;
        this.genre = genre;
    }

    @Override
    public String toString() {
        return name ;
    }

    public void addTicket(){
        numSeats--;
    }




}
