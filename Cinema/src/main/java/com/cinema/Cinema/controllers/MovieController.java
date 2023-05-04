package com.cinema.Cinema.controllers;

import com.cinema.Cinema.entities.Movie;
import com.cinema.Cinema.entities.User;
import com.cinema.Cinema.repositories.MovieRepository;
import com.cinema.Cinema.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
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

    @GetMapping("/admin/showMovies")
    public String showUser(Model model){
        model.addAttribute("movies", movieService.getMovies());
        return "showMovies";
    }

    @GetMapping("/admin/deleteMovies")
    public String deleteMovie(Model model){
        model.addAttribute("movies",movieService.getMovies());
        return "deleteMovies";
    }

    @GetMapping("/admin/deleteMovie/{id}")
    public String deleteMovie(Model model,@PathVariable long id){
        movieService.deleteMovie(id);
        model.addAttribute("movies",movieService.getMovies());
        return "redirect:/admin/showMovies";
    }

    @GetMapping("/admin/modifyMovies")
    public String modifyMovies(Model model){
        model.addAttribute("movies",movieService.getMovies());
        return "modifyMovies";
    }
    @GetMapping("/admin/modifyMovie/{id}")
    public String modifyMovie(Model model,@PathVariable long id){
        model.addAttribute("name", movieService.getMovie(id).getName());
        model.addAttribute("genre", movieService.getMovie(id).getGenre());
        model.addAttribute("numSeats", movieService.getMovie(id).getNumSeats());
        model.addAttribute("numTickets", movieService.getMovie(id).getNumTickets());
        return "modifyMovie";
    }

    @GetMapping("/admin/formEditMovie/{id}")
    public String formMovie(@PathVariable long id, @RequestParam String name, @RequestParam String genre, @RequestParam int numSeats, @RequestParam int numTickets){

        movieService.modifyMovie(id,genre,numSeats);
        return "redirect:/admin/showMovies";
    }

}
