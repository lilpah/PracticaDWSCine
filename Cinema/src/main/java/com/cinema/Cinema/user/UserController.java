package com.cinema.Cinema.user;

import com.cinema.Cinema.ticket.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;

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
    @GetMapping("/user/{id}/showTickets")
    public String showTickets(Model model, @PathVariable Long id){
        User user = userService.getUsers().get(id);
        Collection<Ticket> temp = user.allTickets();
        model.addAttribute("tickets",temp);
        return "ticketsUser";
    }

   @GetMapping("/user/{id}/buyTickets")
    public String buyTickets(Model model, @PathVariable Long id){
        User user = userService.getUsers().get(id);
        Collection<Ticket> temp = user.allTickets();
        model.addAttribute("tickets",temp);
        return "tickets";
    }


   @GetMapping("/user/{id}/formTicket")
    public String formTicket(Model model, @RequestParam String nameMovie, @RequestParam int numSeat, @RequestParam String movieTime, @RequestParam String movieDate,@PathVariable Long id){
        Ticket tmp = new Ticket(nameMovie, numSeat, movieTime, movieDate);
        userService.addTicket(id, tmp);
        return "ticketBookedCorrectly";
    }

    @GetMapping("/user/{id}")
    public String user(Model model,@PathVariable Long id){
        model.addAttribute("name",userService.getUsers().get(id).getName());
        return "userIndex";
    }


}
