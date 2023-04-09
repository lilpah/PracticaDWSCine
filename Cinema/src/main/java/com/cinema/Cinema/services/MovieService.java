package com.cinema.Cinema.services;

import com.cinema.Cinema.entities.Movie;
import com.cinema.Cinema.repositories.MovieRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@Getter
@Setter
public class MovieService {


    @Autowired
    MovieRepository movieRepository;

    public MovieService(){

    }

    public Collection<Movie> getMovies(){
        return movieRepository.findAll();
    }

    public Movie findMovie(long id){
        return movieRepository.findById(id).get();
    }

}
