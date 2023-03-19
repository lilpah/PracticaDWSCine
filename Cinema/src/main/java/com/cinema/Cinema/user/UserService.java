package com.cinema.Cinema.user;

import com.cinema.Cinema.ticket.Ticket;
import lombok.Getter;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
@Getter
public class UserService {

    private Map<Long, User> users = new ConcurrentHashMap<>(); // Integer is for the id of each ticket that each user has

    private AtomicLong lastId = new AtomicLong();
    private AtomicLong lastIdTicket = new AtomicLong();


    public UserService(){
        addUser(new User("nacho","chove","1234","tumadre@gamil"));
        addUser(new User("sanmi","panda","1234","tumadress@gamil"));
        addTicket(1, new Ticket("Creed", 24, "21:24", "3/03/2023"));
        addTicket(2, new Ticket("asdf", 14, "21:24", "3/03/2023"));
    }

    public void addUser(User user){
        long id = lastId.incrementAndGet();
        user.setId(id);
        users.put(id,user);
    }
    public void addTicket(long idUser, Ticket ticket){
        long id = lastIdTicket.incrementAndGet();
        ticket.setIdTicket(id);
        users.get(idUser).getTickets().put(id,ticket);
    }




}
