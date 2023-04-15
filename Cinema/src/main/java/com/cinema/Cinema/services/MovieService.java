package com.cinema.Cinema.services;

import com.cinema.Cinema.entities.Movie;
import com.cinema.Cinema.repositories.MovieRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.concurrent.atomic.AtomicLong;

@Service
@Getter
@Setter
public class MovieService {


    @Autowired
    MovieRepository movieRepository;
    @Autowired
    UserService userService;



    public MovieService(){

    }

    public Collection<Movie> getMovies(){
        return movieRepository.findAll();
    }

    public Movie getMovie(long id){
        return movieRepository.findById(id).get();
    }

    public void deleteMovie(long id){
        Movie movie = movieRepository.findById(id).get();
        userService.deleteMovie(movie);
        movieRepository.deleteById(id);
    }

    public void addMovie(Movie movie){
        movieRepository.save(movie);
    }
}
