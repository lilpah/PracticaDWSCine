package com.cinema.Cinema.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Data
@NoArgsConstructor
@Entity
@Table(name="tickets")
public class Ticket {
    // private long idMovie;

    public interface TicketView {}
    private int numSeat;
    public String nameMovie;
    private String movieTime;
    private String movieDate;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idTicket; // Count the  numbers of tickets a user buy by movie



    @ManyToOne
    //@JsonIgnore
    private Movie movie;


   /* @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<User> users = new ArrayList<>();

    */



    @ManyToOne
    @JsonIgnore
    private User users;



    public Ticket(Movie movie, int numSeat, String movieTime, String movieDate) {
        this.numSeat = numSeat;
        this.movieTime = movieTime;
        this.movieDate = movieDate;
        this.movie = movie;
        this.nameMovie = movie.getName();
    }

}
