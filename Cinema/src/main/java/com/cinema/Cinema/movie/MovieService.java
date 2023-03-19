package com.cinema.Cinema.movie;

import com.cinema.Cinema.ticket.Ticket;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
@Getter
@Setter
public class MovieService {

    private Map<Long, Movie> moviesStorage = new ConcurrentHashMap<>();
    private AtomicLong lastMovie = new AtomicLong();

    public void addMovie(Movie movie){
        if(movie.getNumMovies() == -1){
            long idMovie = lastMovie.incrementAndGet();
            movie.setNumMovies(idMovie);
            moviesStorage.put(idMovie,movie);
        }
    }

}
