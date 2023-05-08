package com.cinema.Cinema.restControllers;

import com.cinema.Cinema.entities.Movie;
import com.cinema.Cinema.entities.Ticket;
import com.cinema.Cinema.entities.User;
import com.cinema.Cinema.repositories.MovieRepository;
import com.cinema.Cinema.repositories.UserRepository;
import com.cinema.Cinema.services.MovieService;
import com.cinema.Cinema.services.UserService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

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



    // ADMIN ROLE ->

    @GetMapping("/admin/showMovies")
    @ResponseStatus(HttpStatus.OK)
    public Collection<Movie> showMovies(Model model){
        return movieService.getMovies();
    }
    @PostMapping("/admin/showMovies")
    @ResponseStatus(HttpStatus.OK)
    public Collection<Movie> showMovies(){
        return userService.getMovies();
    }


    @DeleteMapping("/admin/deleteMovie/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Movie> deleteMovie(@PathVariable long id) {
        if(movieService.getMovie(id) == null){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }else{
            movieService.deleteMovie(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }


    @GetMapping("/admin/addMovie")
    @ResponseStatus(HttpStatus.CREATED)
    public void addMovie(@RequestParam String name, @RequestParam String genre){
        movieService.addMovie(new Movie(name,genre));
    }

    @PostMapping("/admin/addMovie")
    @ResponseStatus(HttpStatus.CREATED)
    public void addMovie2(@RequestBody Movie movie){
        movieService.addMovie(movie);
    }


    @PutMapping("/admin/updateMovie/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable long id, @RequestBody Movie updateMovie){
        if(movieService.getMovie(id) == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else{
            movieService.modifyMovie(id, updateMovie.getGenre(), updateMovie.getNumSeats());
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

    }


}
