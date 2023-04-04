package com.cinema.Cinema.entities;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@Data
@NoArgsConstructor
@Entity
public class Movie {
    private String name;
    private String genre;
    private int numSeats = 100;
    private int numTickets=0;
    @Id
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
        numTickets++;
    }
    public void deleteTicket(){
        numSeats++;
        numTickets--;
    }




}
