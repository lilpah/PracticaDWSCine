package com.cinema.Cinema.controllers;

import com.cinema.Cinema.entities.Movie;
import com.cinema.Cinema.entities.Ticket;
import com.cinema.Cinema.entities.User;
import com.cinema.Cinema.repositories.MovieRepository;
import com.cinema.Cinema.repositories.TicketRepository;
import com.cinema.Cinema.repositories.UserRepository;
import com.cinema.Cinema.services.MovieService;
import com.cinema.Cinema.services.TicketService;
import com.cinema.Cinema.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.List;

@Controller
public class TicketController {

    @Autowired
    UserService userService;
    @Autowired
    MovieService movieService;
    @Autowired
    TicketService ticketService;



    @GetMapping("/user/{id}/deleteTicket")
    public String deleteTicket(Model model, @PathVariable Long id){
        User user = userService.getUser(id);
        model.addAttribute("tickets", user.getTickets());
        return "deleteATicket";
    }

    @GetMapping("/user/{id}/deleteTicket/{idTicket}")
    public String formDeleteTicket(@PathVariable long idTicket, @PathVariable long id){
        userService.deleteTicket(id,idTicket);
        return "ticketDeletedCorrectly";
    }

    @GetMapping("/user/{id}/modifyTicket")
    public String modifyTickets(Model model, @PathVariable Long id){
        User user = userService.getUser(id);
        Collection<Ticket> temp = user.getTickets();
        model.addAttribute("tickets",temp);
        return "modifyTickets";
    }
    @GetMapping("/user/{id}/modifyTicket/{idTicket}")
    public String modifyTicket(Model model,@PathVariable long idTicket,@PathVariable long id){
        model.addAttribute("numSeat",userService.getUser(id).getATicket(idTicket).getNumSeat());
        model.addAttribute("idMovie",userService.getUser(id).getATicket(idTicket).getMovie().getId());
        model.addAttribute("nameMovie",userService.getUser(id).getATicket(idTicket).getMovie().getName());
        model.addAttribute("movieTime",userService.getUser(id).getATicket(idTicket).getMovieTime());
        model.addAttribute("movieDate",userService.getUser(id).getATicket(idTicket).getMovieDate());
        return "modifyATicket";
    }
    @GetMapping("/user/{id}/formTicket/{idTicket}")
    public String formTicket(@RequestParam int numSeat, @RequestParam String movieTime, @RequestParam String movieDate, @PathVariable Long id,@PathVariable Long idTicket){
       /* userService.getUser(id).get().getATicket(idTicket).setNumSeat(numSeat);
        userService.getUser(id).get().getATicket(idTicket).setMovieTime(movieTime);
        userService.getUser(id).get().getATicket(idTicket).setMovieDate(movieDate);

        */

        Ticket newTicket = new Ticket(ticketService.getTicket(idTicket).getMovie(), numSeat, movieTime, movieDate);
        userService.modifyTicket(id, newTicket, idTicket);


        return "ticketsModified";
    }

    @GetMapping("/user/{id}/showTickets")
    public String showTickets(Model model, @PathVariable Long id){
        model.addAttribute("tickets", userService.getUser(id).getTickets());
        return "ticketsUser";
    }

    @GetMapping("/user/{id}/buyTickets")
    public String buyTickets(Model model, @PathVariable Long id){
        User user = userService.getUser(id);
        model.addAttribute("movies", movieService.getMovies());
        model.addAttribute("name", user.getName());
        return "tickets";
    }


    @GetMapping("/user/{id}/formTicket")
    public String formTicket(Model model, @RequestParam long idMovie, @RequestParam int numSeat, @RequestParam String movieTime, @RequestParam String movieDate, @PathVariable Long id){
        Ticket tmp = new Ticket(movieService.getMovie(idMovie), numSeat, movieTime, movieDate);
        userService.addTicket(id, tmp);
        return "ticketBookedCorrectly";
    }

    @GetMapping("reviews")
    public String showMovies(Model model){
        model.addAttribute("movies",movieService.getMovies());
        return "reviews";
    }


    @GetMapping("usersByMovie")
    public String findByOccupancy(Model model, @RequestParam long idMovie){
        model.addAttribute("idMovie", movieService.getMovie(idMovie).getName());
        List<User> users = ticketService.getUserByMovie(idMovie);
        model.addAttribute("users", users);
        return "usersByMovies";
    }
}














