package com.cinema.Cinema.restControllers;

import com.cinema.Cinema.entities.Movie;
import com.cinema.Cinema.entities.Ticket;
import com.cinema.Cinema.entities.User;
import com.cinema.Cinema.services.MovieService;
import com.cinema.Cinema.services.TicketService;
import com.cinema.Cinema.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

@RestController
@RequestMapping("/api")
public class TicketRESTController {

    @Autowired
    UserService userService;
    @Autowired
    MovieService movieService;

    @Autowired
    TicketService ticketService;

    @GetMapping("/user/buyTicket")
    @ResponseStatus(HttpStatus.CREATED)
    public void buyTicket(@RequestParam long idMovie, @RequestParam int numSeat, @RequestParam String movieTime, @RequestParam String movieDate, HttpServletRequest request) {
        Ticket tmp = new Ticket(movieService.getMovie(idMovie), numSeat, movieTime, movieDate);
        String name = request.getUserPrincipal().getName();
        User user = userService.findByName(name).orElseThrow();
        userService.addTicket(user.getId(), tmp);
        //return userService.getUsers().get(id).allTickets().toString();
    }

    // Este no funciona
    @PostMapping("/user/buyTicket")
    @ResponseStatus(HttpStatus.CREATED)
    public void buyTicket2(@RequestBody Ticket ticket, HttpServletRequest request) {
        //Ticket tmp = new Ticket(movieService.getMovie(idMovie), numSeat, movieTime, movieDate);
        String name = request.getUserPrincipal().getName();
        User user = userService.findByName(name).orElseThrow();
        userService.addTicket(user.getId(), ticket);
        //return userService.getUser(id).getTickets().toString();
    }



    @PostMapping("/user/showTickets")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Collection<Ticket>> showTickets2(HttpServletRequest request) {
        if(SecurityContextHolder.getContext().getAuthentication().isAuthenticated()){
            String name = request.getUserPrincipal().getName();
            User user = userService.findByName(name).orElseThrow();
            return new ResponseEntity<>(userService.getUser(user.getId()).getTickets(), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/user/showTickets")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Collection<Ticket>> showTickets(HttpServletRequest request) {
        if(SecurityContextHolder.getContext().getAuthentication().isAuthenticated()){
            String name = request.getUserPrincipal().getName();
            User user = userService.findByName(name).orElseThrow();
            return new ResponseEntity<>(userService.getUser(user.getId()).getTickets(), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }


   @DeleteMapping("/user/deleteTicket/{idTicket}")
   @ResponseStatus(HttpStatus.OK)
   public ResponseEntity<User> deleteTicket(HttpServletRequest request, @PathVariable long idTicket) {
       String name = request.getUserPrincipal().getName();
       User user = userService.findByName(name).orElseThrow();
        if(userService.getUser(user.getId()).getATicket(idTicket) != null) {
            userService.deleteTicket(user.getId(), idTicket);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/user/updateTicket/{idTicket}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Ticket> setUpdateTicket(HttpServletRequest request, @PathVariable long idTicket, @RequestBody Ticket updateTicket) {
        //Ticket tmp = new Ticket(idMovie, numSeat, movieTime, movieDate);
        //return userService.getUsers().get(id).allTickets().toString();

        String name = request.getUserPrincipal().getName();
        User user = userService.findByName(name).orElseThrow();
        if(userService.getUser(user.getId()).getATicket(idTicket) != null) {
            //ticketService.getTicket(idTicket).setNameMovie(updateTicket.nameMovie);

            //updateTicket.setNameMovie(ticketService.getTicket(idTicket).getNameMovie());
            userService.modifyTicket(user.getId(), updateTicket, idTicket);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/user/{id}/getMovie")
    @ResponseStatus(HttpStatus.CREATED)
    public Movie getMovie(Model model, @RequestParam long idMovie, @RequestParam int numSeat, @RequestParam String movieTime, @RequestParam String movieDate, @PathVariable long id) {
        System.out.println(movieService.getMovie(idMovie));
        Ticket tmp = new Ticket(movieService.getMovie(idMovie), numSeat, movieTime, movieDate);
        return tmp.getMovie();
        //return userService.getUsers().get(id).allTickets().toString();
    }


    @GetMapping("/user/{id}/getTicket")
    @ResponseStatus(HttpStatus.CREATED)
    public Ticket getTicket(Model model, @RequestParam long idMovie, @RequestParam int numSeat, @RequestParam String movieTime, @RequestParam String movieDate, @PathVariable long id) {
        System.out.println(movieService.getMovie(idMovie));
        Ticket tmp = new Ticket(movieService.getMovie(idMovie), numSeat, movieTime, movieDate);
        return tmp;
        //return userService.getUsers().get(id).allTickets().toString();
    }






}
