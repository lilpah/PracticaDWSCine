package com.cinema.Cinema.ticket;

import com.cinema.Cinema.user.User;
import com.cinema.Cinema.user.UserService;
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
    public String buyTicket(Model model, @RequestParam long nameMovie, @RequestParam int numSeat, @RequestParam String movieTime, @RequestParam String movieDate, @PathVariable Long id) {
        Ticket tmp = new Ticket(nameMovie, numSeat, movieTime, movieDate);
        userService.addTicket(id, tmp);
        return userService.getUsers().get(id).allTickets().toString();
    }

    @PostMapping("/user/{id}/buyTicket")
    @ResponseStatus(HttpStatus.CREATED)
    public void buyTicket2(Model model, @RequestBody long nameMovie, @RequestBody int numSeat, @RequestBody String movieTime, @RequestBody String movieDate, @PathVariable Long id) {
        Ticket tmp = new Ticket(nameMovie, numSeat, movieTime, movieDate);
        userService.addTicket(id, tmp);
        //return userService.getUsers().get(id).allTickets().toString();
    }

    @GetMapping("/user/{id}/showTickets")
    public ResponseEntity<Collection<Ticket>> showTickets(Model model, @PathVariable Long id) {
        if(userService.getUsers().get(id).getTickets() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        User user = userService.getUsers().get(id);
        Collection<Ticket> temp = user.allTickets();
        model.addAttribute("tickets", temp);
        return new ResponseEntity<>(user.allTickets(), HttpStatus.OK);
    }


    @PostMapping("/user/{id}/showTickets")
    public ResponseEntity<Collection<Ticket>> showTickets2(Model model, @PathVariable Long id) {
        if(userService.getUsers().get(id).getTickets() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        User user = userService.getUsers().get(id);
        Collection<Ticket> temp = user.allTickets();
        model.addAttribute("tickets", temp);
        return new ResponseEntity<>(user.allTickets(), HttpStatus.OK);
    }



   @DeleteMapping("/user/{id}/deleteTicket/{idTicket}")
   @ResponseStatus(HttpStatus.OK)
   public ResponseEntity<User> deleteTicket(@PathVariable long id, @PathVariable long idTicket) {
        if(userService.getUsers().get(id).allTickets() != null) {
            userService.deleteTicket(id,idTicket);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PutMapping("/user/{id}/modifiyTicket/{idTicket}")
    @ResponseStatus(HttpStatus.CREATED)
    public void modifyTicket(Model model, @RequestParam long nameMovie, @RequestParam int numSeat, @RequestParam String movieTime, @RequestParam String movieDate, @PathVariable Long id, @PathVariable Long idTicket) {
        Ticket tmp = new Ticket(nameMovie, numSeat, movieTime, movieDate);
        userService.modifyTicket(id, tmp);
        //return userService.getUsers().get(id).allTickets().toString();
    }


}
