package com.cinema.Cinema.user;

import com.cinema.Cinema.ticket.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

@Controller
public class UserController {
    @Autowired
    UserService userService;
    @GetMapping("/showUsers")
    public String showUser(Model model){
        Collection<User> temp = userService.getUsers().values();
        model.addAttribute("users",temp);
        return "usersTable";
    }
    @GetMapping("/showTickets/{id}")
    public String showTickets(Model model, @PathVariable Long id){
        User user = userService.getUsers().get(id);
        Collection<Ticket> temp = user.allTickets();
        model.addAttribute("tickets",temp);
        return "ticketsUser";
    }

   /*@GetMapping("/formTicket")
    public String formTicket(Model model, @RequestParam String nameMovie, @RequestParam int numSeat, @RequestParam String movieTime, @RequestParam String movieDate){
        Ticket tmp = new Ticket(nameMovie, numSeat, movieTime, movieDate);
        userService.addTicket(userService.getUsers().get(id), tmp);
        return "ticketBookedCorrectly";
    }*/

}
