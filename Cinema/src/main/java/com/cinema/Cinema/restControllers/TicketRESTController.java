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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/user/{id}/buyTicket")
    @ResponseStatus(HttpStatus.CREATED)
    public void buyTicket(Model model, @RequestParam long idMovie, @RequestParam int numSeat, @RequestParam String movieTime, @RequestParam String movieDate, @PathVariable long id) {
        Ticket tmp = new Ticket(movieService.getMovie(idMovie), numSeat, movieTime, movieDate);
        userService.addTicket(id, tmp);
        //return userService.getUsers().get(id).allTickets().toString();
    }

    @PostMapping("/user/{id}/buyTicket/{idMovie}")
    @ResponseStatus(HttpStatus.CREATED)
    public void buyTicket2(@RequestBody Ticket ticket, @PathVariable long idMovie, @PathVariable long id) {
        //Ticket tmp = new Ticket(movieService.getMovie(idMovie), numSeat, movieTime, movieDate);
        userService.addTicket2(id, ticket, idMovie);
        //return userService.getUser(id).getTickets().toString();
    }

    @PostMapping("/user/{id}/showTickets")
    public ResponseEntity<Collection<Ticket>> showTickets2(Model model, @PathVariable long id) {
        if(userService.getUser(id).getTickets() == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        User user = userService.getUser(id);
        Collection<Ticket> temp = user.getTickets();
        model.addAttribute("tickets", temp);
        return new ResponseEntity<>(user.getTickets(), HttpStatus.OK);

    }


    @GetMapping("/user/{id}/showTickets")
    public ResponseEntity<Collection<Ticket>> showTickets(Model model, @PathVariable long id) {
        if(userService.getUser(id).getTickets() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        User user = userService.getUser(id);
        Collection<Ticket> temp = user.getTickets();
        model.addAttribute("tickets", temp);
        return new ResponseEntity<>(user.getTickets(), HttpStatus.OK);

    }


   @DeleteMapping("/user/{id}/deleteTicket/{idTicket}")
   @ResponseStatus(HttpStatus.OK)
   public ResponseEntity<User> deleteTicket(@PathVariable long id, @PathVariable long idTicket) {
        if(userService.getUser(id).getTickets() != null) {
            userService.deleteTicket(id,idTicket);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/user/{id}/updateTicket/{idTicket}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Ticket> setUpdateTicket(Model model, @PathVariable long id, @PathVariable long idTicket, @RequestBody Ticket updateTicket) {
        //Ticket tmp = new Ticket(idMovie, numSeat, movieTime, movieDate);
        //return userService.getUsers().get(id).allTickets().toString();
        if(userService.getUser(id).getTickets() != null) {
            ticketService.getTicket(idTicket).setNameMovie(updateTicket.nameMovie);

            //updateTicket.setNameMovie(ticketService.getTicket(idTicket).getNameMovie());
            userService.modifyTicket(id, updateTicket, idTicket);
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
