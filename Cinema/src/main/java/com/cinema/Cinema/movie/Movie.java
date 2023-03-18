package com.cinema.Cinema.movie;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Data
@NoArgsConstructor
public class Movie {
    private String name;
    private String genre;
    private int numBusySeats = 0;

    public Movie(String name, String genre) {
        this.name = name;
        this.genre = genre;
    }




}
