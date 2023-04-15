package com.cinema.Cinema.controllers;

import com.cinema.Cinema.entities.Movie;
import com.cinema.Cinema.repositories.MovieRepository;
import com.cinema.Cinema.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MovieController {

    @Autowired
    MovieRepository movieRepository;
    @Autowired
    MovieService movieService;
    @GetMapping("findByOccupancy")
    public String findByOccupancy(Model model, @RequestParam int num){
        model.addAttribute("num", num);
        List<Movie> movies = movieRepository.findMoviesByNumTicketsIsLessThan(num);
        model.addAttribute("movies", movies);
        return "occupancy";
    }

    @GetMapping("deleteMovie")
    public String deleteMovie(Model model){
        model.addAttribute("movies",movieService.getMovies());
        return "deleteMovies";
    }

    @GetMapping("formDeleteMovies")
    public String deleteMovie(Model model,@RequestParam long id){
        movieService.deleteMovie(id);
        model.addAttribute("movies",movieService.getMovies());
        return "deleteMovies";
    }
}
