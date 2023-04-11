package com.cinema.Cinema.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Data
@NoArgsConstructor
@Entity
@Table(name="movies")
public class Movie {
    private String name;
    private String genre;
    private int numSeats = 100;
    private int numTickets = 0;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id = -1;


    @OneToMany(cascade = {CascadeType.PERSIST,CascadeType.REMOVE},mappedBy = "movie")
    @JsonIgnore
    private List<Ticket> ticketsList = new ArrayList<>();

    @ManyToMany(mappedBy = "movies", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private List<User> users = new ArrayList<>();


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
