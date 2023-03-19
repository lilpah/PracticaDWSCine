package com.cinema.Cinema.ticket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TicketController {

    @Autowired
    TicketService ticketService;

    @GetMapping("/tickets/formTicket")
    public String formTicket(Model model, @RequestParam String nameMovie, @RequestParam int numSeat, @RequestParam String movieTime, @RequestParam String movieDate){
        Ticket tmp = new Ticket(nameMovie, numSeat, movieTime, movieDate);
        ticketService.createTicket(tmp);
        return "ticketBookedCorrectly";
    }


}

