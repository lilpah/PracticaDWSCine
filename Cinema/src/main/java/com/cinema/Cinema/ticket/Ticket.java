package com.cinema.Cinema.ticket;
import lombok.*;

import java.util.Map;

@Getter
@Setter
@Data
@NoArgsConstructor
public class Ticket {
    private String nameMovie;
    private int numSeat;
    private String movieTime;
    private String movieDate;
    private int numTicketsByMovie = -1; // Count the  numbers of tickets a user buy by movie

    public Ticket(String nameMovie, int numSeat, String movieTime, String movieDate) {
        this.nameMovie = nameMovie;
        this.numSeat = numSeat;
        this.movieTime = movieTime;
        this.movieDate = movieDate;
    }


}
