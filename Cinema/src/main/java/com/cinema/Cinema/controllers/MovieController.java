package com.cinema.Cinema.controllers;

import com.cinema.Cinema.entities.Movie;
import com.cinema.Cinema.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MovieController {

    @Autowired
    MovieRepository movieRepository;
    @GetMapping("findByOccupancy")
    public String findByOccupancy(Model model, @RequestParam int num){
        model.addAttribute("num", num);
        List<Movie> movies = movieRepository.findMoviesByNumTicketsIsLessThan(num);
        model.addAttribute("movies", movies);
        return "occupancy";
    }
}
