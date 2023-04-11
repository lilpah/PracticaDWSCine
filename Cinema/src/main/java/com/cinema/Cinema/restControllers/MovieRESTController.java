package com.cinema.Cinema.restControllers;

import com.cinema.Cinema.repositories.MovieRepository;
import com.cinema.Cinema.repositories.UserRepository;
import com.cinema.Cinema.services.MovieService;
import com.cinema.Cinema.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class MovieRESTController {
    @Autowired
    UserService userService;
    @Autowired
    MovieService movieService;

    @GetMapping("/numTicketsByMovie/{idMovie}")
    @ResponseStatus(HttpStatus.CREATED)
    public int numTicketsByMovie(@PathVariable long idMovie) {
       // return userService.getMovies().get(idMovie).getNumTickets();
        return movieService.getMovie(idMovie).getNumTickets();
    }

    @PostMapping("/numTicketsByMovie/{idMovie}")
    @ResponseStatus(HttpStatus.CREATED)
    public int numTicketByMovie2(@PathVariable long idMovie) {
        //return userService.getMovies().get(idMovie).getNumTickets();
        return movieService.getMovie(idMovie).getNumTickets();
    }



}
