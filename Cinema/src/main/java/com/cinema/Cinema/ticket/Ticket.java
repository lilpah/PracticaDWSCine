package com.cinema.Cinema.ticket;
import com.cinema.Cinema.movie.Movie;
import com.cinema.Cinema.user.UserService;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Getter
@Setter
@Data
@NoArgsConstructor

public class Ticket {
    private long idMovie;
    private int numSeat;
    public String nameMovie;
    private String movieTime;
    private String movieDate;
    private long idTicket; // Count the  numbers of tickets a user buy by movie


    public Ticket(long idMovie, int numSeat, String movieTime, String movieDate) {
        this.idMovie = idMovie;
        this.numSeat = numSeat;
        this.movieTime = movieTime;
        this.movieDate = movieDate;
    }


}
