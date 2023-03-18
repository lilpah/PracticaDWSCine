package com.cinema.Cinema.ticket;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class TicketService {
    private Map<Long, Ticket> tickets = new ConcurrentHashMap<>();
    private AtomicLong lastId = new AtomicLong();

     public TicketService(){
         tickets.put((long)1, new Ticket("Creed", 24, "21:24", "3/03/2023"));
         tickets.put((long)1, new Ticket("asdf", 14, "21:24", "3/03/2023"));
     }
    public Collection<Ticket> getAll(){
        return tickets.values();
    }

    public Ticket createTicket(Ticket ticket){
        long temp = lastId.incrementAndGet();
        ticket.setIdTicket(temp);
        tickets.put(temp, ticket);
        return ticket;
    }


    @GetMapping("showTicket/{id}")
    public String showTicket(Model model, @PathVariable Integer id){
        Ticket temp = tickets.get(id - 1);
        model.addAttribute("nameMovie", temp.getNameMovie());
        model.addAttribute("numSeat", temp.getNumSeat());
        model.addAttribute("movieTime", temp.getMovieTime());
        model.addAttribute("movieDate", temp.getMovieDate());
        return "ticketsBooked";
    }


}
