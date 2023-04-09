package com.cinema.Cinema.restControllers;

import com.cinema.Cinema.entities.Ticket;
import com.cinema.Cinema.entities.User;
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

    @GetMapping("/user/{id}/buyTicket")
    @ResponseStatus(HttpStatus.CREATED)
    public String buyTicket(Model model, @RequestParam long idMovie, @RequestParam int numSeat, @RequestParam String movieTime, @RequestParam String movieDate, @PathVariable long id) {
        Ticket tmp = new Ticket(idMovie, numSeat, movieTime, movieDate);
        userService.addTicket(id, tmp);
        //return userService.getUsers().get(id).allTickets().toString();
        return "a";

    }

    @PostMapping("/user/{id}/buyTicket")
    @ResponseStatus(HttpStatus.CREATED)
    public String buyTicket2(Model model, @RequestBody Ticket ticket, @PathVariable long id) {
        userService.addTicket(id, ticket);
        //return userService.getUsers().get(id).allTickets().toString();
        return "a";
    }

  /*  @PostMapping("/user/{id}/showTickets")
    public ResponseEntity<Collection<Ticket>> showTickets2(Model model, @PathVariable long id) {
        if(userService.getUsers().get(id).getTickets() == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        /*User user = userService.getUsers().get(id);
        Collection<Ticket> temp = user.allTickets();
        model.addAttribute("tickets", temp);
        return new ResponseEntity<>(user.allTickets(), HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

    }


    @GetMapping("/user/{id}/showTickets")
    public ResponseEntity<Collection<Ticket>> showTickets(Model model, @PathVariable long id) {
        if(userService.getUsers().get(id).getTickets() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
      /*  User user = userService.getUsers().get(id);
        Collection<Ticket> temp = user.allTickets();
        model.addAttribute("tickets", temp);
        return new ResponseEntity<>(user.allTickets(), HttpStatus.OK);


        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

    }

   */



   @DeleteMapping("/user/{id}/deleteTicket/{idTicket}")
   @ResponseStatus(HttpStatus.OK)
   public ResponseEntity<User> deleteTicket(@PathVariable long id, @PathVariable long idTicket) {
       /* if(userService.getUsers().get(id).allTickets() != null) {
            userService.deleteTicket(id,idTicket);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        */
       return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @PutMapping("/user/{id}/updateTicket/{idTicket}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Ticket> setUpdateTicket(Model model, @PathVariable long id, @PathVariable long idTicket, @RequestBody Ticket updateTicket) {
        //Ticket tmp = new Ticket(idMovie, numSeat, movieTime, movieDate);
        //return userService.getUsers().get(id).allTickets().toString();
      /*  if(userService.getUsers().get(id).getTickets().get(idTicket) != null) {
            userService.modifyTicket(id, updateTicket, userService.getUsers().get(id).getTickets().get(idTicket));
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

       */
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

    }




}
