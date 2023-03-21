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


    public Collection<Ticket> getAll(){
        return tickets.values();
    }

    public void createTicket(Ticket ticket){
        long temp = lastId.incrementAndGet();
        ticket.setIdTicket(temp);
        tickets.put(temp, ticket);
    }




    // Isma te recuerda que mires datables.net

}
