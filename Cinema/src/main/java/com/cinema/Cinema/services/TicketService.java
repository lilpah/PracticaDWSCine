package com.cinema.Cinema.services;

import com.cinema.Cinema.entities.Ticket;
import com.cinema.Cinema.entities.User;
import com.cinema.Cinema.repositories.TicketRepository;
import com.cinema.Cinema.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class TicketService {
    @Autowired
    TicketRepository ticketRepository;

    private Map<Long, Ticket> tickets = new ConcurrentHashMap<>();
    private AtomicLong lastId = new AtomicLong();


    public Collection<Ticket> getAll(){
        return tickets.values();
    }

    public void createTicket(Ticket ticket){
        long temp = lastId.incrementAndGet();
        //ticket.setIdTicket(temp);
        ticketRepository.save(ticket);
       // tickets.put(temp, ticket);
    }




    // Isma te recuerda que mires datables.net

}
