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
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
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
    @Autowired
    UserRepository userRepository;



    @GetMapping("/user/deleteTicket")
    public String deleteTicket(Model model, HttpServletRequest request){
        String name = request.getUserPrincipal().getName();
        User user = userRepository.findByName(name).orElseThrow();
        CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
        model.addAttribute("token", token.getToken());

        long id = user.getId();
        model.addAttribute("tickets", user.getTickets());
        return "deleteATicket";
    }

    @GetMapping("/user/deleteTicket/{idTicket}")
    public String formDeleteTicket(Model model, @PathVariable long idTicket, HttpServletRequest request){
        String name = request.getUserPrincipal().getName();
        User user = userRepository.findByName(name).orElseThrow();
        CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
        model.addAttribute("token", token.getToken());

        long id = user.getId();
        userService.deleteTicket(id,idTicket);
        return "redirect:/user/showTickets";
    }

    @GetMapping("/user/modifyTicket")
    public String modifyTickets(Model model, HttpServletRequest request){
        String name = request.getUserPrincipal().getName();
        User user = userRepository.findByName(name).orElseThrow();
        CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
        model.addAttribute("token", token.getToken());

        long id = user.getId();

        Collection<Ticket> temp = user.getTickets();
        model.addAttribute("tickets",temp);
        return "modifyTickets";
    }
    @GetMapping("/user/modifyTicket/{idTicket}")
    public String modifyTicket(Model model,@PathVariable long idTicket,HttpServletRequest request){
        String name = request.getUserPrincipal().getName();
        User user = userRepository.findByName(name).orElseThrow();
        CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
        model.addAttribute("token", token.getToken());

        long id = user.getId();

        model.addAttribute("numSeat",userService.getUser(id).getATicket(idTicket).getNumSeat());
        model.addAttribute("idMovie",userService.getUser(id).getATicket(idTicket).getMovie().getId());
        model.addAttribute("nameMovie",userService.getUser(id).getATicket(idTicket).getMovie().getName());
        model.addAttribute("movieTime",userService.getUser(id).getATicket(idTicket).getMovieTime());
        model.addAttribute("movieDate",userService.getUser(id).getATicket(idTicket).getMovieDate());
        return "modifyATicket";
    }
    @GetMapping("/user/formTicket/{idTicket}")
    public String formTicket(Model model, @RequestParam int numSeat, @RequestParam String movieTime, @RequestParam String movieDate, HttpServletRequest request, @PathVariable Long idTicket){
        String name = request.getUserPrincipal().getName();
        User user = userRepository.findByName(name).orElseThrow();
        CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
        model.addAttribute("token", token.getToken());

        long id = user.getId();

        Ticket newTicket = new Ticket(ticketService.getTicket(idTicket).getMovie(), numSeat, movieTime, movieDate);
        userService.modifyTicket(id, newTicket, idTicket);


        return "redirect:/user/showTickets";
    }

    @GetMapping("/user/showTickets")
    public String showTickets(Model model, HttpServletRequest request){
        String name = request.getUserPrincipal().getName();
        User user = userRepository.findByName(name).orElseThrow();
        CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
        model.addAttribute("token", token.getToken());

        long id = user.getId();
        model.addAttribute("tickets", userService.getUser(id).getTickets());
        return "ticketsUser";
    }

    @GetMapping("/user/buyTickets")
    public String buyTickets(Model model, HttpServletRequest request){
        String name = request.getUserPrincipal().getName();
        User user = userRepository.findByName(name).orElseThrow();
        CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
        model.addAttribute("token", token.getToken());
        model.addAttribute("movies", movieService.getMovies());
        model.addAttribute("name", user.getName());
        return "tickets";
    }


    @GetMapping("/user/formTicket")
    public String formTicket(Model model, @RequestParam long idMovie, @RequestParam int numSeat, @RequestParam String movieTime, @RequestParam String movieDate,HttpServletRequest request){
        Ticket tmp = new Ticket(movieService.getMovie(idMovie), numSeat, movieTime, movieDate);
        String name = request.getUserPrincipal().getName();
        User user = userRepository.findByName(name).orElseThrow();
        CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
        model.addAttribute("token", token.getToken());

        long id = user.getId();
        userService.addTicket(id, tmp);
        return "redirect:showTickets";
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














